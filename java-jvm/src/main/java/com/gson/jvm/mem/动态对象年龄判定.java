package com.gson.jvm.mem;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintTenuringDistribution
 */
public class 动态对象年龄判定 {
    private static final int _1_MB = 1024 * 1024;

    /**
     * Survivor区中同代年龄对象大小的总和 >= Survivor区大小一半时，大于等于该年龄的对象就会进入到老年代，不用到达固定年龄。
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        byte[] allocate1, allocate2, allocate3, allocate4;
        // 分配到eden区
        allocate1 = new byte[_1_MB/4];
        // 分配到eden区
        allocate2 = new byte[_1_MB/4];
        // 分配到eden区
        allocate3 = new byte[4 * _1_MB];

        // eden区不足以装下allocate4, 进行一次minor gc, allocate1,allocate2 进入 to survivor,
        // allocate3太大，to survivor装不下，由空间分配担保机制，知allocate3进入老年代。
        // allocate4分配到eden区
        allocate4 = new byte[4* _1_MB];

        // allocate4指针为空，则之前指向的4M eden区堆内存成为了垃圾,但是还未回收
        allocate4 = null;
        // 再向eden区申请4M内存，发现不够了，因为eden区已经有4M内存(垃圾) +其他一些零星的内存
        // 于是进行第二次minor gc, 发现from survivor区的allocate1,allocate2占用512kb，达到survivor区一半大小
        // 所以allocate1,allocate2进入老年大， allocate4之前指向的4M被当作垃圾回收掉。
        // 故gc后，新生代已使用内存为0
        allocate4 = new byte[4 * _1_MB];
        Thread.sleep(2000);
    }
}
