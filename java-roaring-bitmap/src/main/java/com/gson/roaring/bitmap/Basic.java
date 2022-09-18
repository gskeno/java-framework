package com.gson.roaring.bitmap;

import org.roaringbitmap.RoaringBitmap;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Basic {
    public static void main(String[] args) throws IOException {
        // RoaringBitmap.RoaringArray keys 数组
        // 由于1，2，3，1000 四个元素的高16位都是0，所以keys数组只需要一个元素
        int[] elements = {1, 2, 3, 1000, Integer.MAX_VALUE - 1};
        RoaringBitmap rr = RoaringBitmap.bitmapOf(elements);

        boolean b1 = rr.runOptimize();
        System.out.println("exit run-length optimize:" + b1);


        // would return the third value or 1000
        // 查找第j小的元素，key是有序排列的，container也是有序排列的，ArrayContainer也是有序排列的
        // j的最小下标是0，所以结果为1000
        int select = rr.select(3);
        System.out.println("select 3th:" + select + " in " + Arrays.toString(elements));

        // would return the rank of 2, which is index 1
        int rank = rr.rank(2);
        System.out.println("element 2 rank:" + rank + " in " + Arrays.toString(elements));

        // 0不在
        rank = rr.rank(0);
        System.out.println("element 0 rank:" + rank + " in " + Arrays.toString(elements));

        // will return true
        boolean contains = rr.contains(1000);
        System.out.println("1000 contains in " + Arrays.toString(elements) + " :" + contains);
        // will return false
        contains = rr.contains(7);
        System.out.println("7 contains in " + Arrays.toString(elements) + " :" + contains);

        RoaringBitmap rr2 = new RoaringBitmap();
        rr2.add(4000L, 4255L);
        // new bitmap
        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);
        // in-place computation
        // 内置计算，rr会被更新
        rr.or(rr2);
        // true
        boolean equals = rror.equals(rr);
        if (!equals) throw new RuntimeException("bug");
        // number of values stored?
        long cardinality = rr.getLongCardinality();
        System.out.println("elements  cardinality " + cardinality);
        // a "forEach" is faster than this loop, but a loop is possible:
        for (int i : rr) {
            System.out.println(i);
        }

        // 是否存在步长优化, 有RunContainer的时候，才会有步长优化
        System.out.println("before runOptimize, serializedSizeInBytes=" + rr.serializedSizeInBytes());
        rr.runOptimize();
        // 获取需要的字节大小，看源码
        System.out.println("after runOptimize, serializedSizeInBytes=" + rr.serializedSizeInBytes());

        // 序列化到byte数组中去
        ByteBuffer byteBuffer = ByteBuffer.allocate(rr.serializedSizeInBytes());
        rr.serialize(byteBuffer);
        byte[] array = byteBuffer.array();
        System.out.println(array.length);

        // byte[] ---> ByteBuffer ---> RoaringBitmap
        RoaringBitmap bitmap = new RoaringBitmap();
        bitmap.deserialize(ByteBuffer.wrap(array));
        // 序列化之后，再反序列化，两者元素是一致的，所以equals
        System.out.println(bitmap.equals(rr));
    }

}
