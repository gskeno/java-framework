package com.gson.javajdk.jvmgc;

import java.lang.ref.SoftReference;

/**
 * vm options
 *  * -XX:NewRatio=2 -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *  * 堆最大值 堆初始值  打印gc日志
 * @date 2021/1/30
 */
public class SoftReferenceGCTest {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test1(){
        //老年代占用5m

        SoftReference<byte[]> reference = new SoftReference<>(new byte[5 * 1024 * 1024]);
        System.gc();

        System.out.println("-----");

        //因为内存空间不足，Allocation Failure，触发软引用失效,第一个5m的数组被回收
        byte[] b = new byte[6*1024*1024];
    }

    private static void test2(){
        //老年代占用5m
        byte[] a = new byte[5*1024*1024];
        SoftReference<byte[]> reference = new SoftReference<>(a);
        System.gc();

        System.out.println("-----");

        //因为内存空间不足，Allocation Failure，触发软引用失效,
        //但a指针仍然指向该数组，所以a无法回收，会内存溢出
        byte[] b = new byte[6*1024*1024];
    }
}
