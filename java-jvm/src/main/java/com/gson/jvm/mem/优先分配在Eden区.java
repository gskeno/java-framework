package com.gson.jvm.mem;

/**
 * vm参数
 * -XX:+UseSerialGC  参数可以指定年轻代和老年代都使用串行收集器。
 * 等价于新生代使用Serial GC，并且老年代使用Serial Old GC.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 */
public class 优先分配在Eden区 {
    // 1M对应的字节数
    private static final int _1_MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        testAllocation();
        Thread.sleep(4000);
    }
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1_MB];
        allocation2 = new byte[2 * _1_MB];
        allocation3 = new byte[2 * _1_MB];
        allocation4 = new byte[4 * _1_MB];
    }
}
