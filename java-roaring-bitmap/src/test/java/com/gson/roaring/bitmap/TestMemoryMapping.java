/*
 * (c) the authors Licensed under the Apache License, Version 2.0.
 */

package com.gson.roaring.bitmap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.roaringbitmap.IntIterator;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.BufferFastAggregation;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


class ByteBufferBackedInputStream extends InputStream {

  ByteBuffer buf;

  ByteBufferBackedInputStream(ByteBuffer buf) {
    this.buf = buf;
  }

  @Override
  public int available() throws IOException {
    return buf.remaining();
  }

  @Override
  public boolean markSupported() {
    return false;
  }

  @Override
  public int read() throws IOException {
    if (!buf.hasRemaining()) {
      return -1;
    }
    return 0xFF & buf.get();
  }

  @Override
  public int read(byte[] bytes) throws IOException {
    int len = Math.min(bytes.length, buf.remaining());
    buf.get(bytes, 0, len);
    return len;
  }

  @Override
  public int read(byte[] bytes, int off, int len) throws IOException {
    len = Math.min(len, buf.remaining());
    buf.get(bytes, off, len);
    return len;
  }

  @Override
  public long skip(long n) {
    int len = Math.min((int) n, buf.remaining());
    buf.position(buf.position() + (int) n);
    return len;
  }
}


class ByteBufferBackedOutputStream extends OutputStream {
  ByteBuffer buf;

  ByteBufferBackedOutputStream(ByteBuffer buf) {
    this.buf = buf;
  }

  @Override
  public synchronized void write(byte[] bytes) throws IOException {
    buf.put(bytes);
  }

  @Override
  public synchronized void write(byte[] bytes, int off, int len) throws IOException {
    buf.put(bytes, off, len);
  }

  @Override
  public synchronized void write(int b) throws IOException {
    buf.put((byte) b);
  }

}


public class TestMemoryMapping {

  // 序列化后，写到文件中；再从文件中读取出来，进行反序列化，构造出来的ImmutableRoaringBitmap
  static ArrayList<ImmutableRoaringBitmap> mappedbitmaps = new ArrayList<ImmutableRoaringBitmap>();

  static MappedByteBuffer out;

  // 序列化前的MutableRoaringBitmap
  static ArrayList<MutableRoaringBitmap> rambitmaps = new ArrayList<MutableRoaringBitmap>();

  static File tmpfile;

  @AfterAll
  public static void clearFiles() {
    System.out.println("[TestMemoryMapping] Cleaning memory-mapped file.");
    out = null;
    rambitmaps = null;
    mappedbitmaps = null;
    tmpfile.delete();
  }

  public static boolean equals(ByteBuffer bb1, ByteBuffer bb2) {
    if (bb1.limit() != bb2.limit()) {
      return false;
    }
    for (int k = 0; k < bb1.limit(); ++k) {
      if (bb1.get(k) != bb2.get(k)) {
        return false;
      }
    }
    return true;
  }

  @BeforeAll
  public static void initFiles() throws IOException {
    System.out.println("[TestMemoryMapping] Setting up memory-mapped file. (Can take some time.)");
    final ArrayList<Long> offsets = new ArrayList<Long>();
    tmpfile = File.createTempFile("roaring", "bin");
    System.out.println("tmpFile: " + tmpfile.getAbsolutePath());
    // jvm 退出时删除文件
    tmpfile.deleteOnExit();
    final FileOutputStream fos = new FileOutputStream(tmpfile);
    final DataOutputStream dos = new DataOutputStream(fos);
    // 65536 = 64 * 2^10 = 2^16 正好等于2个字节,等价short类型或者char类型
    // 65536是16Bit所能表示的数字的个数
    for (int N = 65536 * 16; N <= 65536 * 128; N *= 8) {
      for (int gap = 1; gap <= 65536; gap *= 4) {
        // 4^8 == 65536，所以rb1一定new了8+1=9次
        final MutableRoaringBitmap rb1 = new MutableRoaringBitmap();
        for (int x = 0; x < N; x += gap) {
          // 所add元素的间距为gap
          rb1.add(x);
        }
        // make containers 8 and 10 be run encoded
        // container跟key是一一对应的, 高16位为8（或者10）对应着Container_8(或者Container_10)
        for (int x = 8 * 65536; x < 8 * 65536 + 1000; ++x) {
          rb1.add(x);
        }
        for (int x = 10 * 65536; x < 10 * 65536 + 1000; ++x) {
          rb1.add(x);
          rb1.add(10000 + x);
        }
        {
          RoaringBitmap rr = RoaringBitmap.bitmapOf(rb1.toArray());
          ByteBuffer bb = toByteBuffer(rb1);
          // MutableRoaringBitmap--->ByteBuffer与 MutableRoaringBitmap--->int[]--->RoaringBitmap--->ByteBuffer相比
          if (!equals(toByteBuffer(rr), bb)) {
            throw new RuntimeException("serialized output not identical.");
          }
          // 倒带, position重设为0
          bb.rewind();
          // byteBuffer 反序列化为 RaringBitmap
          RoaringBitmap rr2 = toRoaringBitmap(bb);
          if (!rr2.equals(rr)) {
            throw new RuntimeException("Can't recover RoaringBitmap");
          }
          // 因为ByteBuffer反序列化了，所以bb的position不再是0了，需要再次倒带
          bb.rewind();
          MutableRoaringBitmap rb2 = toMutableRoaringBitmap(bb);
          if (!rb1.equals(rb2)) {
            throw new RuntimeException("Can't recover MutableRoaringBitmap");
          }
          // 因为ByteBuffer反序列化了，所以bb的position不再是0了，需要再次倒带
          bb.rewind();
          ImmutableRoaringBitmap irb = new ImmutableRoaringBitmap(bb);
          // 这里的equals有重写，因为rb1是MutableRoaringBitmap类型，是ImmutableRoaringBitmap的子类，这里重写比较后认为相等
          if (!irb.equals(rb1)) {
            throw new RuntimeException("serialized output cannot be mapped.");
          }
        }

        rb1.runOptimize();

        {
          RoaringBitmap rr = RoaringBitmap.bitmapOf(rb1.toArray());
          rr.runOptimize();
          ByteBuffer bb = toByteBuffer(rb1);
          if (!equals(toByteBuffer(rr), bb)) {
            throw new RuntimeException("serialized output not identical.");
          }
          bb.rewind();
          RoaringBitmap rr2 = toRoaringBitmap(bb);
          if (!rr2.equals(rr)) {
            throw new RuntimeException("Can't recover RoaringBitmap");
          }
          bb.rewind();
          MutableRoaringBitmap rb2 = toMutableRoaringBitmap(bb);
          if (!rb1.equals(rb2)) {
            throw new RuntimeException("Can't recover MutableRoaringBitmap");
          }
          bb.rewind();
          ImmutableRoaringBitmap irb = new ImmutableRoaringBitmap(bb);
          if (!irb.equals(rb1)) {
            throw new RuntimeException("serialized output cannot be mapped.");
          }
        }

        rambitmaps.add(rb1);
        // 第一次执行时fos.getChannel().position()==0， 随后的serialize会将字节写入到dos中，position会自动变化
        offsets.add(fos.getChannel().position());
        rb1.serialize(dos);
        dos.flush();
        for (int offset = 1; offset <= gap; offset *= 8) {
          final MutableRoaringBitmap rb2 = new MutableRoaringBitmap();
          for (int x = 0; x < N; x += gap) {
            rb2.add(x + offset);
          }
          // gap 1 gives runcontainers
          rb2.runOptimize();
          offsets.add(fos.getChannel().position());
          long pbef = fos.getChannel().position();
          rb2.serialize(dos);
          long paft = fos.getChannel().position();
          if (paft - pbef != rb2.serializedSizeInBytes()) {
            throw new RuntimeException("wrong serializedSizeInBytes:: paft-pbef = " + (paft - pbef)
                + ", serializedSize = " + rb2.serializedSizeInBytes());
          }
          dos.flush();
          rambitmaps.add(rb2);
          // we add tests
          // MutableRoaringBitmap ---> ByteBuffer
          ByteBuffer outbb = ByteBuffer.allocate(rb2.serializedSizeInBytes());
          rb2.serialize(new DataOutputStream(new ByteBufferBackedOutputStream(outbb)));
          // 切换成读模式
          outbb.flip();
          ImmutableRoaringBitmap irb = new ImmutableRoaringBitmap(outbb);
          if (!irb.equals(rb2)) {
            throw new RuntimeException("No hope of working");
          }
        }
      }
    }
    final long totalcount = fos.getChannel().position();
    System.out.println("[TestMemoryMapping] Wrote " + totalcount / 1024 + " KB");
    offsets.add(totalcount); // offsets 比 rambitmaps 多一个元素
    dos.close();
    final RandomAccessFile memoryMappedFile = new RandomAccessFile(tmpfile, "r");
    try {
      // 文件映射进虚拟内存
      out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, totalcount);
      final long bef = System.currentTimeMillis();
      for (int k = 0; k < offsets.size() - 1; ++k) {
        // 做数据分隔，将out当前的position到limit的数据分隔出来
        final ByteBuffer bb = out.slice();
        // Next commented line is not required nor recommended
        // bb.limit((int) (offsets.get(k+1)-offsets.get(k)));
        // 最大的魅力就在这里，这里没有说明读取bb多少个字节，但是bb满足RoaringBitmapSpec结构，所以构造
        // 函数内部能解析出来应该读取多少个字节
        ImmutableRoaringBitmap newbitmap = new ImmutableRoaringBitmap(bb);
        if (newbitmap.serializedSizeInBytes() != rambitmaps.get(k).serializedSizeInBytes()) {
          throw new RuntimeException(
              "faulty reported serialization size " + newbitmap.serializedSizeInBytes() + " "
                  + rambitmaps.get(k).serializedSizeInBytes());
        }
        if (!newbitmap.equals(rambitmaps.get(k))) {
          throw new RuntimeException("faulty serialization");
        }
        mappedbitmaps.add(newbitmap);
        out.position(out.position() + newbitmap.serializedSizeInBytes());
        if (out.position() != offsets.get(k + 1).longValue()) {
          throw new RuntimeException("faulty serialization");
        }
      }
      final long aft = System.currentTimeMillis();
      System.out.println("[TestMemoryMapping] Mapped " + (offsets.size() - 1) + " bitmaps in "
          + (aft - bef) + "ms");
    } finally {
      memoryMappedFile.close();
    }
  }

  public static ByteBuffer toByteBuffer(MutableRoaringBitmap rb) {
    // we add tests
    ByteBuffer outbb = ByteBuffer.allocate(rb.serializedSizeInBytes());
    try {
      rb.serialize(new DataOutputStream(new ByteBufferBackedOutputStream(outbb)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Buffer有两种模式，在写模式下调用flip后，会变为读模式
    outbb.flip();
    // 设置字节序
    outbb.order(ByteOrder.LITTLE_ENDIAN);
    return outbb;
  }

  public static ByteBuffer toByteBuffer(RoaringBitmap rb) {
    // we add tests
    ByteBuffer outbb = ByteBuffer.allocate(rb.serializedSizeInBytes());
    try {
      rb.serialize(new DataOutputStream(new ByteBufferBackedOutputStream(outbb)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    //
    outbb.flip();
    outbb.order(ByteOrder.LITTLE_ENDIAN);
    return outbb;
  }

  public static MutableRoaringBitmap toMutableRoaringBitmap(ByteBuffer bb) {
    MutableRoaringBitmap rb = new MutableRoaringBitmap();
    try {
      rb.deserialize(new DataInputStream(new ByteBufferBackedInputStream(bb)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rb;
  }

  /**
   * 反序列化
   * @param bb
   * @return
   */
  public static RoaringBitmap toRoaringBitmap(ByteBuffer bb) {
    RoaringBitmap rb = new RoaringBitmap();
    try {
      rb.deserialize(new DataInputStream(new ByteBufferBackedInputStream(bb)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rb;
  }

  @Test
  public void basic() {
    System.out.println("[TestMemoryMapping] basic tests");
    for (int k = 0; k < mappedbitmaps.size(); ++k) {
      // 序列化前的 == 反序列化后的
      assertTrue(mappedbitmaps.get(k).equals(rambitmaps.get(k)));
    }
  }

  @Test
  /**
   * 补充，与某某相匹配
   */
  public void complements() {
    System.out.println("[TestMemoryMapping] testing complements");
    for (int k = 0; k < mappedbitmaps.size() - 1; k += 4) {
      final MutableRoaringBitmap rb =
          ImmutableRoaringBitmap.andNot(mappedbitmaps.get(k), mappedbitmaps.get(k + 1));
      final MutableRoaringBitmap rbram =
          ImmutableRoaringBitmap.andNot(rambitmaps.get(k), rambitmaps.get(k + 1));
      assertTrue(rb.equals(rbram));
    }
  }

  /**
   * 测试相交
   */
  @Test
  public void intersections() {
    System.out.println("[TestMemoryMapping] testing intersections");
    for (int k = 0; k + 1 < mappedbitmaps.size(); k += 2) {
      final MutableRoaringBitmap rb =
          ImmutableRoaringBitmap.and(mappedbitmaps.get(k), mappedbitmaps.get(k + 1));
      final MutableRoaringBitmap rbram =
          ImmutableRoaringBitmap.and(rambitmaps.get(k), rambitmaps.get(k + 1));
      assertTrue(rb.equals(rbram));
    }

    for (int k = 0; k < mappedbitmaps.size() - 4; k += 4) {
      final MutableRoaringBitmap rb = BufferFastAggregation.and(mappedbitmaps.get(k),
          mappedbitmaps.get(k + 1), mappedbitmaps.get(k + 3), mappedbitmaps.get(k + 4));
      final MutableRoaringBitmap rbram = BufferFastAggregation.and(rambitmaps.get(k),
          rambitmaps.get(k + 1), rambitmaps.get(k + 3), rambitmaps.get(k + 4));
      assertTrue(rb.equals(rbram));
    }
  }



  

  @Test
  public void oneFormat() throws IOException {
    System.out.println("[TestMemoryMapping] testing format compatibility");
    final int ms = mappedbitmaps.size();
    for (int k = 0; k < ms; ++k) {
      System.out.println("[TestMemoryMapping] testing compat. bitmap " + k + " out of " + ms);
      ImmutableRoaringBitmap rr = mappedbitmaps.get(k);
      ByteArrayOutputStream bos = new ByteArrayOutputStream(rr.serializedSizeInBytes());
      DataOutputStream dos = new DataOutputStream(bos);
      rr.serialize(dos);
      dos.close();
      byte[] arr = bos.toByteArray();
      bos = null;
      ByteArrayInputStream bis = new ByteArrayInputStream(arr);
      RoaringBitmap newr = new RoaringBitmap();
      newr.deserialize(new DataInputStream(bis));
      arr = null;
      RoaringBitmap rrasroaring = rr.toRoaringBitmap();
      assertEquals(newr, rrasroaring);
      System.out
          .println("[TestMemoryMapping] testing compat. bitmap " + k + " out of " + ms + ". ok.");
    }
    System.out.println("[TestMemoryMapping] Format compatibility ok");

  }

  @Test
  public void reserialize() throws IOException {
    System.out.println("[TestMemoryMapping] testing reserialization");
    for (int k = 0; k < mappedbitmaps.size(); ++k) {
      ImmutableRoaringBitmap rr = mappedbitmaps.get(k);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bos);
      rr.serialize(dos);
      dos.close();
      ByteBuffer bb = ByteBuffer.wrap(bos.toByteArray());
      ImmutableRoaringBitmap rrback = new ImmutableRoaringBitmap(bb);
      assertTrue(rr.equals(rrback));
      assertTrue(rr.equals(rrback.toMutableRoaringBitmap()));
      assertTrue(rr.toMutableRoaringBitmap().equals(rrback));
      assertTrue(rr.toMutableRoaringBitmap().equals(rambitmaps.get(k)));
    }
  }



  @Test
  public void testIterator() {
    System.out.println("[TestMemoryMapping] test iterators");
    final int ms = mappedbitmaps.size();
    System.out.println("We first test in-memory (RoaringBitmap) iterators.");
    for (int k = 0; k < ms; ++k) {
      System.out.println("[TestMemoryMapping] testing copy via iterators using RoaringBitmap copy "
          + k + " out of " + ms);
      final RoaringBitmap target = mappedbitmaps.get(k).toRoaringBitmap();
      final int truecard = target.getCardinality();
      System.out.println("Cardinality = " + truecard);
      int card1 = 0;
      int oldvalue = -1;
      long t1 = System.nanoTime();
      for (int x : target) {
        assertTrue(target.contains(x));
        if (x > oldvalue) {
          ++card1;
        }
        oldvalue = x;
      }
      long t2 = System.nanoTime();
      System.out.println(" iterator one ns/ops = " + (t2 - t1) * 1.0 / truecard);
      assertEquals(truecard, card1);
      long t3 = System.nanoTime();
      IntIterator i = target.getIntIterator();
      oldvalue = -1;
      int card2 = 0;
      while (i.hasNext()) {
        final int x = i.next();
        assertTrue(target.contains(x));
        if (x > oldvalue) {
          ++card2;
        }
        oldvalue = x;
      }
      long t4 = System.nanoTime();
      System.out.println(" iterator two ns/ops = " + (t4 - t3) * 1.0 / truecard);
      assertEquals(truecard, card2);
      System.out.println("[TestMemoryMapping] testing copy via iterators using RoaringBitmap copy "
          + k + " out of " + ms + " ok");
    }

    System.out.println("Next, we test mapped (ImmutableRoaringBitmap) iterators.");
    for (int k = 0; k < ms; ++k) {
      System.out.println("[TestMemoryMapping] testing copy via iterators " + k + " out of " + ms);
      final ImmutableRoaringBitmap target = mappedbitmaps.get(k);
      final ImmutableRoaringBitmap ramtarget = rambitmaps.get(k);
      assertEquals(target, ramtarget);
      final int truecard = target.getCardinality();
      System.out.println("Cardinality = " + truecard);
      int card1 = 0;
      int oldvalue = -1;
      long t1 = System.nanoTime();
      for (int x : target) {
        assertTrue(ramtarget.contains(x));
        if (x > oldvalue) {
          ++card1;
        }
        oldvalue = x;
      }
      long t2 = System.nanoTime();
      System.out.println(" iterator one ns/ops = " + (t2 - t1) * 1.0 / truecard);
      assertEquals(truecard, card1);
      long t3 = System.nanoTime();
      IntIterator i = target.getIntIterator();
      oldvalue = -1;
      int card2 = 0;
      while (i.hasNext()) {
        final int x = i.next();
        assertTrue(ramtarget.contains(x));
        if (x > oldvalue) {
          ++card2;
        }
        oldvalue = x;
      }
      long t4 = System.nanoTime();
      System.out.println(" iterator two ns/ops = " + (t4 - t3) * 1.0 / truecard);
      assertEquals(truecard, card2);
      System.out
          .println("[TestMemoryMapping] testing copy via iterators " + k + " out of " + ms + " ok");
    }
    System.out.println("[TestMemoryMapping] testing a custom iterator copy  ");

    MutableRoaringBitmap rb = new MutableRoaringBitmap();
    for (int k = 0; k < 4000; ++k) {
      rb.add(k);
    }
    for (int k = 0; k < 1000; ++k) {
      rb.add(k * 100);
    }
    MutableRoaringBitmap copy1 = new MutableRoaringBitmap();
    for (int x : rb) {
      copy1.add(x);
    }
    assertTrue(copy1.equals(rb));
    MutableRoaringBitmap copy2 = new MutableRoaringBitmap();
    IntIterator i = rb.getIntIterator();
    while (i.hasNext()) {
      copy2.add(i.next());
    }
    assertTrue(copy2.equals(rb));
    System.out.println("[TestMemoryMapping] testing a custom iterator copy  ok");
  }

  @Test
  public void unions() {
    System.out.println("[TestMemoryMapping] testing Unions");
    for (int k = 0; k < mappedbitmaps.size() - 4; k += 4) {
      final MutableRoaringBitmap rb = BufferFastAggregation.or(mappedbitmaps.get(k),
          mappedbitmaps.get(k + 1), mappedbitmaps.get(k + 3), mappedbitmaps.get(k + 4));
      final MutableRoaringBitmap rbram = BufferFastAggregation.or(rambitmaps.get(k),
          rambitmaps.get(k + 1), rambitmaps.get(k + 3), rambitmaps.get(k + 4));
      assertTrue(rb.equals(rbram));
    }
  }

  @Test
  public void XORs() {
    System.out.println("[TestMemoryMapping] testing XORs");
    for (int k = 0; k < mappedbitmaps.size() - 4; k += 4) {
      final MutableRoaringBitmap rb = BufferFastAggregation.xor(mappedbitmaps.get(k),
          mappedbitmaps.get(k + 1), mappedbitmaps.get(k + 3), mappedbitmaps.get(k + 4));
      final MutableRoaringBitmap rbram = BufferFastAggregation.xor(rambitmaps.get(k),
          rambitmaps.get(k + 1), rambitmaps.get(k + 3), rambitmaps.get(k + 4));
      assertTrue(rb.equals(rbram));
    }
  }
}
