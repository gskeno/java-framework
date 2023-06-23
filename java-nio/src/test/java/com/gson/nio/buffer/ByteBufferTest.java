package com.gson.nio.buffer;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.put((byte) 1);
        bb.put((byte) 1);
        bb.put((byte) 1);
        bb.put((byte) 1);
        //
        bb.clear();
        int anInt = bb.getInt();
        System.out.println(Integer.toHexString(anInt));
    }
}
