package com.gson.roaring.bitmap;

import org.junit.jupiter.api.Test;
import org.roaringbitmap.RoaringBitmap;

import java.nio.ByteBuffer;

public class BaseTest {
    @Test
    public void test(){
        RoaringBitmap roaringBitmap = RoaringBitmap.bitmapOf();
        System.out.println("original serializedSizeInBytes=" + roaringBitmap.serializedSizeInBytes());
        System.out.println("original sizeInBytes=" + roaringBitmap.getSizeInBytes());

        for (int i = 1000; i < 1010; i++) {
            if (i>0){
                roaringBitmap.add(i);
            }
            int bytesSize = roaringBitmap.serializedSizeInBytes();
            System.out.println("serializedSizeInBytes=" + bytesSize);
            int sizeInBytes = roaringBitmap.getSizeInBytes();
            System.out.println("sizeInBytes=" + sizeInBytes);


            ByteBuffer byteBuffer = ByteBuffer.allocate(roaringBitmap.serializedSizeInBytes());
            roaringBitmap.serialize(byteBuffer);
            byte[] array = byteBuffer.array();
            System.out.println(array.length);
        }
    }

    @Test
    public void test1(){
        int value = 1000000 * 8;
        RoaringBitmap roaringBitmap = RoaringBitmap.bitmapOf();
        for (int i = 0; i < value; i=i+2) {
            roaringBitmap.add(i);
        }
        roaringBitmap.runOptimize();
        System.out.println(roaringBitmap.serializedSizeInBytes());
    }

    @Test
    public void test2(){
        int value = 400000 / 8;
        System.out.println("第40万商家的标需要多少个字节:" + value /1024 + "KB");
    }
}
