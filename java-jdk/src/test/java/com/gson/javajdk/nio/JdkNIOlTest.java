package com.gson.javajdk.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Set;

/**
 * https://www.cnblogs.com/niejunlei/p/5997244.html
 */
public class JdkNIOlTest {
    @Test
    public void testMMap() throws Exception {
        System.out.println(System.getProperty("java.io.tmpdir"));
        File tempFile = File.createTempFile("mmapTest", null);
        System.out.println(tempFile.getAbsolutePath());
        RandomAccessFile file = new RandomAccessFile(tempFile, "rw");

        FileChannel channel = file.getChannel();

        ByteBuffer temp = ByteBuffer.allocate (100);
        // Put something in the file, starting at location 0
        temp.put ("This is the file content".getBytes( ));
        // reset buffer inner status and flag, then transfer buffer data to another
        temp.flip( );
        channel.write (temp, 0);

        // Put something else in the file, starting at location 8192.
        // 8192 is 8 KB, almost certainly a different memory/FS page.
        // This may cause a file hole, depending on the
        // filesystem page size.
        temp.clear( );
        temp.put ("This is more file content".getBytes( ));
        temp.flip( );
        channel.write (temp, 8192);

        // Create three types of mappings to the same file
        // real implement class is DirectByteBuffer
        MappedByteBuffer ro = channel.map (
                FileChannel.MapMode.READ_ONLY, 0, channel.size( ));
        MappedByteBuffer rw = channel.map (
                FileChannel.MapMode.READ_WRITE, 0, channel.size( ));
        MappedByteBuffer cow = channel.map (
                FileChannel.MapMode.PRIVATE, 0, channel.size( ));
        showBuffers (ro, rw, cow);
        // Modify the copy-on-write buffer
        cow.position (8);
        cow.put ("COW".getBytes( ));
        System.out.println ("Change to COW buffer");
        showBuffers (ro, rw, cow);
        // Modify the read/write buffer92
        rw.position (9);
        rw.put (" R/W ".getBytes( ));
        rw.position (8194);
        rw.put (" R/W ".getBytes( ));
        rw.force( );
        System.out.println ("Change to R/W buffer");
        showBuffers (ro, rw, cow);
        // Write to the file through the channel; hit both pages
        temp.clear( );
        temp.put ("Channel write ".getBytes( ));
        temp.flip( );
        channel.write (temp, 0);
        temp.rewind( );
        channel.write (temp, 8202);

        System.out.println ("Write on channel");
        showBuffers (ro, rw, cow);
        // Modify the copy-on-write buffer again
        cow.position (8207);
        cow.put (" COW2 ".getBytes( ));
        System.out.println ("Second change to COW buffer");
        showBuffers (ro, rw, cow);
        // Modify the read/write buffer
        rw.position (0);
        rw.put (" R/W2 ".getBytes( ));
        rw.position (8210);
        rw.put (" R/W2 ".getBytes( ));
        rw.force( );
        System.out.println ("Second change to R/W buffer");
        showBuffers (ro, rw, cow);
        // cleanup
        channel.close( );
        file.close( );
        tempFile.delete( );
    }

    // Show the current content of the three buffers
    public static void showBuffers (ByteBuffer ro, ByteBuffer rw, ByteBuffer cow) throws Exception{
        dumpBuffer ("R/O", ro);
        dumpBuffer ("R/W", rw);
        dumpBuffer ("COW", cow);
        System.out.println ("");
    }
    // Dump buffer content, counting and skipping nulls
    public static void dumpBuffer (String prefix, ByteBuffer buffer) throws Exception {
        System.out.print (prefix + ": '");
        int nulls = 0;
        int limit = buffer.limit( );
        for (int i = 0; i < limit; i++) {
            char c = (char) buffer.get (i);
            if (c == '\u0000') {
                nulls++;
                continue;
            }
            if (nulls != 0) {
                System.out.print ("|[" + nulls
                        + " nulls]|");
                nulls = 0;
            }
            System.out.print (c);
        }
        System.out.println ("'");
    }


    /**
     * byte-->channel, 数据写到file channel中，会存储到磁盘上
     * @throws IOException
     */
    @Test
    public void testWriteToChannel() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("temp.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(400);
        byteBuffer.put((byte) 64);
        byteBuffer.put((byte) 65);
        // buffer convert write mode to read mode
        // 写模式切换到读模式
        byteBuffer.flip();

        // buffer --->channel
        int writeBytes = channel.write(byteBuffer);
        System.out.println(writeBytes);
    }

    @Test
    public void readFromChannel() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("temp.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        int readByteCount;
        while ((readByteCount = channel.read(byteBuffer)) != -1){
            System.out.println("readByteCount=" + readByteCount);
            byteBuffer.flip();
            for (int i = 0; i < readByteCount; i++) {
                // HeapByteBuffer
                byte element = byteBuffer.get(i);
                System.out.println(element);
            }
            byteBuffer.clear();
        }
    }

    @Test
    public void testByteBufferRewind() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("rewind.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.put((byte)65);
        byteBuffer.put((byte)66);
        byteBuffer.put((byte)67);
        byteBuffer.put((byte)68);
        byteBuffer.put((byte)69);

        // bytebuffer由写模式切换到读模式
        byteBuffer.flip();
        // bytebuffer写到channel， byteBuffer已经被读取一遍
        channel.write(byteBuffer);

        // rewind设置position = 0, 可以再读一遍bytebuffer
        byteBuffer.rewind();

        byte[] dest = new byte[5];
        byteBuffer.get(dest);

        System.out.println(Arrays.toString(dest));
    }

    @Test
    public void testMark(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.put((byte)65);
        byteBuffer.put((byte)66);
        byteBuffer.put((byte)67);
        byteBuffer.put((byte)68);
        byteBuffer.put((byte)69);

        byteBuffer.flip();

        byte b = byteBuffer.get();
    }

    @Test
    public void testSocketChannel() throws IOException {
        // 建立一个Selector
        Selector selector = Selector.open();

        // 建立一个serverSocketChannel
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(inetSocketAddress);

        // channel设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // channel注册到selector上，关心channel的read,write,connet,accept就绪事件
        // 注: ServerSocketChannel只接受注册ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int readyChannels = selector.selectNow();
            if(readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()){
                    System.out.println("key.isAcceptable");
                }else if (key.isConnectable()){
                    System.out.println("key.isConnectable");
                }else if (key.isReadable()){
                    System.out.println("key.isReadable");
                }else if (key.isWritable()){
                    System.out.println("key.isWritable");
                }
                keyIterator.remove();
            }
        }

    }

    @Test
    public void test(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        ByteOrder order = byteBuffer.order();
        System.out.println(order);
    }
}
