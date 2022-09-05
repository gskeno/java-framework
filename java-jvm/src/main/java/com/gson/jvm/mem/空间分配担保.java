package com.gson.jvm.mem;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintTenuringDistribution
 * // HandlePromotionFailure在jdk8中不生效。只要老年代剩余大小 大于新生代对象总大小或者大于新生代历次晋升老年大对象平均值大小，则minor gc
 * -XX:-HandlePromotionFailure
 */
public class 空间分配担保 {
    private static final int _1_MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        byte[] allocate1, allocate2, allocate3, allocate4, allocate5, allocate6, allocate7;
        allocate1 = new byte[3*_1_MB];
        allocate2 = new byte[3*_1_MB];
        allocate3 = new byte[3*_1_MB];
        allocate4 = new byte[3*_1_MB];
//        allocate1 = new byte[2*_1_MB];
//        allocate2 = new byte[2*_1_MB];
//        allocate3 = new byte[2*_1_MB];
//        allocate1 = null;
//
//        allocate4 = new byte[2*_1_MB];
//        allocate5 = new byte[2*_1_MB];
//        allocate6 = new byte[3*_1_MB];
//        allocate4 = null;
//        allocate5 = null;
//        allocate6 = null;
        allocate6 = new byte[4*_1_MB];
        allocate6 = null;
        allocate7 = new byte[1*_1_MB];
        Thread.sleep(2000);
    }
}
