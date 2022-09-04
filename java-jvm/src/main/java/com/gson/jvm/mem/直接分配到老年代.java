package com.gson.jvm.mem;

/**
 * vm参数
 * -XX:+UseSerialGC  参数可以指定年轻代和老年代都使用串行收集器。
 * 等价于新生代使用Serial GC，并且老年代使用Serial Old GC.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:PrintGCDetails -XX:PretenureSizeThreshold=3145728
 */
public class 直接分配到老年代 {

    public static void main(String[] args) throws InterruptedException {
        byte[] allocation = new byte[4 * 1024 *1024];
        Thread.sleep(2000);
    }
}
