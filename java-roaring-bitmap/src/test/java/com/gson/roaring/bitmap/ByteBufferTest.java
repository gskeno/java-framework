package com.gson.roaring.bitmap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ByteBufferTest {

    @Test
    public void basic(){
        int capacity = 20;
        int size = 10;
        // mark <= position <= limit <= capacity
        ByteBuffer bf = ByteBuffer.allocate(capacity);
        for (int i = 1; i <= size ; i++) {
            // 写数据, position自增
            bf.put((byte)i);
        }
        int position = bf.position();
        int limit = bf.limit();
        int bfcapacity = bf.capacity();
        assert position == 10;
        // 后10个元素被allocate自动初始化为0
        assert limit == 20;
        assert bfcapacity == 20;

        // limit 设置为当前position, position设置为0，mark设置为-1
        // 因为上面put了10个元素，所以现在flip后，可以读取10个元素
        // 一般用在写模式切换到读模式时
        bf.flip();
        for (int index = 0; index < size; index++) {
            // 读模式下，bf内部position属性不会自增
            byte b = bf.get(index);
            // 因为元素值是1至10
            assert index + 1 == b;
        }
        assert bf.capacity() == 20;
        assert bf.position() == 0;
        assert bf.limit() == 10;

        // bf的position到limit之间的数据切割成目标片段，且slice.limit = slice.capacity
        // slice会同步影响新旧的ByteBuffer
        ByteBuffer slice = bf.slice();
        assert slice.position() == 0;
        assert slice.limit() == 10;
        assert slice.capacity() == 10;

    }

    @Test
    public void testDirectByteBuffer(){
        ByteBuffer directBf = ByteBuffer.allocateDirect(2000);
    }

    @Test
    public void testRoaringBitmap(){
        int capacity = 200;
        MutableRoaringBitmap mutableRoaringBitmap = new MutableRoaringBitmap();
        for (int i = 0; i < capacity; i++) {
            mutableRoaringBitmap.add(i);
        }
        int[] elements = mutableRoaringBitmap.toArray();

        // roaringBitmap ---> byteBuffer
        RoaringBitmap roaringBitmap = RoaringBitmap.bitmapOf(elements);
        ByteBuffer byteBuffer = ByteBuffer.allocate(roaringBitmap.serializedSizeInBytes());
        roaringBitmap.serialize(byteBuffer);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.flip();

        ImmutableRoaringBitmap ird = new ImmutableRoaringBitmap(byteBuffer);
        ImmutableRoaringBitmap immutableRoaringBitmap = mutableRoaringBitmap.toImmutableRoaringBitmap();


        ByteBuffer bf1 = ByteBuffer.allocate(capacity);
        for (int i = 0; i < capacity; i++) {
            bf1.put((byte)i);
        }
        bf1.order(ByteOrder.LITTLE_ENDIAN);
        bf1.flip();
        ImmutableRoaringBitmap ird1 = new ImmutableRoaringBitmap(bf1);
        System.out.println(ird1);
    }

    /**
     * 共享底层数组
     */
    @Test
    public void testSlice(){
        ByteBuffer bf = ByteBuffer.allocate(20);
        for (int i = 0; i < 20; i++) {
            bf.put((byte)i);
        }

        bf.position(10);
        ByteBuffer slice = bf.slice();
        System.out.println(slice);
        slice.put((byte)23);
        System.out.println(slice);
    }


    @Test
    public void multithreadingTest() throws InterruptedException, IOException {
        System.out.println("[TestMemoryMapping] multithreading test");
        final MutableRoaringBitmap rr1 = new MutableRoaringBitmap();

        final int numThreads = Runtime.getRuntime().availableProcessors();
        final Throwable[] errors = new Throwable[numThreads];

        for (int i = 0; i < numThreads; i++) {
            // each thread will check an integer from a different container
            // i=0,1,2时，高16位为0，共用一个Container
            rr1.add(Short.MAX_VALUE * i);
        }

        // 会产生4个Container

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        rr1.serialize(dos);
        dos.close();
        ByteBuffer bb = ByteBuffer.wrap(bos.toByteArray());
        final ImmutableRoaringBitmap rrback1 = new ImmutableRoaringBitmap(bb);

        final CountDownLatch ready = new CountDownLatch(numThreads);
        final CountDownLatch finished = new CountDownLatch(numThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int ti = i;
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    ready.countDown();
                    try {
                        ready.await();
                        final int elementToCheck = Short.MAX_VALUE * ti;
                        for (int j = 0; j < 10000000; j++) {
                            try {
                                assertTrue(rrback1.contains(elementToCheck));
                            } catch (Throwable t) {
                                errors[ti] = t;
                            }
                        }
                    } catch (Throwable e) {
                        errors[ti] = e;
                    }
                    finished.countDown();
                }
            });
        }
        finished.await(5, TimeUnit.SECONDS);
        for (int i = 0; i < numThreads; i++) {
            if (errors[i] != null) {
                errors[i].printStackTrace();
                fail("The contains() for the element " + Short.MAX_VALUE * i + " throw an exception");
            }
        }
    }
}
