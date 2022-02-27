package com.gson.roaring.bitmap;

import org.roaringbitmap.Container;
import org.roaringbitmap.ContainerPointer;
import org.roaringbitmap.RoaringArray;
import org.roaringbitmap.RoaringBitmap;

import java.util.Arrays;
import java.util.Objects;

public class Basic {
    public static void main(String[] args) {
        // RoaringBitmap.RoaringArray keys 数组
        // 由于1，2，3，1000 四个元素的高16位都是0，所以keys数组只需要一个元素
        int[] elements = {1,2,3,1000, Integer.MAX_VALUE - 1};
        RoaringBitmap rr = RoaringBitmap.bitmapOf(elements);



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
        rr2.add(4000L,4255L);
        // new bitmap
        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);
        // in-place computation
        // 内置计算，rr会被更新
        rr.or(rr2);
        // true
        boolean equals = rror.equals(rr);
        if(!equals) throw new RuntimeException("bug");
        // number of values stored?
        long cardinality = rr.getLongCardinality();
        System.out.println("elements  cardinality " + cardinality);
        // a "forEach" is faster than this loop, but a loop is possible:
        for(int i : rr) {
             System.out.println(i);
        }
    }
}
