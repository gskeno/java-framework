package com.gson.jvm.mem;

/**
 * -XX:+UseSerialGC  参数可以指定年轻代和老年代都使用串行收集器。
 * 等价于新生代使用Serial GC，并且老年代使用Serial Old GC.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 */
public class 长期存活的对象进入老年代 {
    private static final int _1_MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1_MB/4];
        allocation2 = new byte[4 * _1_MB];
        allocation3 = new byte[4 * _1_MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1_MB];
        //Thread.sleep(1000);
    }
}
