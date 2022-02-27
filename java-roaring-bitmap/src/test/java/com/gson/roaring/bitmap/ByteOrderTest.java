package com.gson.roaring.bitmap;

import java.nio.ByteOrder;

public class ByteOrderTest {

    public static void main(String[] args) {
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        // 小端序
        System.out.println(byteOrder);
    }
}
