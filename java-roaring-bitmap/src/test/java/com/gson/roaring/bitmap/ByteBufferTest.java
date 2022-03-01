package com.gson.roaring.bitmap;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

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
        // 一般用在写模式切换到读模式时
        bf.flip();
        for (int index = 0; index < size; index++) {
            // 读模式下，bf内部position属性不会自增
            byte b = bf.get(index);
            // 因为元素值是1至10
            assert index + 1 == b;
        }
        ByteBuffer slice = bf.slice();
        System.out.println(slice);

    }
}
