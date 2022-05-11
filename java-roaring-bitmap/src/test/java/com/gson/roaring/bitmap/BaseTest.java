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

        for (int i = 0; i < 100; i++) {
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
        // 如果某用户入会了第4万号商家，设其标位为 40000，用传统的bitmap，大概需要 40000/8 = 5000B，即5千个字节
        // 而用RoaringBitmap只需要两个字节，用0号桶,用0号桶映射的0号Container(
        // 实现是ArrayContainer, 内部有一个可变长度的short[]), short数组的第一个元素是8
        int value = 40000 / 8;
        System.out.println("第4万商家的标需要多少个字节:" + value  + "B");
    }
}
