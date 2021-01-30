package com.gson.javajdk.jvmgc;
/**
 * vm options
 * -XX:NewRatio=2 -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 * 堆最大值 堆初始值  打印gc日志
 * @date 2021/1/30
 */
public class AllocationFailureTest {

    public static void main(String[] args) throws InterruptedException {
        byte[] bytesA = new byte[6 *1024 *1024];
        System.gc();

//        bytesA = null;
//        bytesA = new byte[6*1024*1024];
//        System.gc();

    }
}
