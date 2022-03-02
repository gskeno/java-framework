package com.gson.roaring.bitmap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
}
