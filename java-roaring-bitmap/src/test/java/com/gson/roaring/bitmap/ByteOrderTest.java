package com.gson.roaring.bitmap;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class ByteOrderTest {

    public static void main(String[] args) {
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        // 小端序
        System.out.println(byteOrder);
    }

    /**
     * 大小端序列
     */
    @Test
    public void testBigLittleEndian(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(20);
        ByteBuffer buf = buffer.order() == LITTLE_ENDIAN ? buffer : buffer.slice().order(LITTLE_ENDIAN);

        // 低地址往高地址依次放入0，1，....，19
        for (int i = 0; i < 20; i++) {
            buf.put((byte) i);
        }
        for (int i = 0; i < 20; i++) {
            // 低地址往高地址依次取出0，1，...，19
            System.out.println(buf.get(i));
        }
        System.out.println("-------------A");
        for (int i = 0; i < 20; i++) {
            System.out.println(buffer.get(i));
        }
        buffer.position(buffer.position() + buf.position());
        System.out.println("-------------B");
        for (int i = 0; i < 20; i++) {
            System.out.println(buf.get(i));
        }

    }
}
