package com.gson.algo.huawei;

import org.junit.Test;

public class HJ63Test {
    @Test
    public void test(){
        System.out.println(HJ63DNA序列.getSubStr("ACGT", 2));
    }

    @Test
    public void test1(){
        System.out.println(HJ63DNA序列.getSubStr("AAATCACGGAGAAACCAGGTCAGCTGTCTTTACCTCGC", 19));
        System.out.println(HJ63DNA序列.getSubStr2("AAATCACGGAGAAACCAGGTCAGCTGTCTTTACCTCGC", 19));
        System.out.println(HJ63DNA序列.getSubStr3("AAATCACGGAGAAACCAGGTCAGCTGTCTTTACCTCGC", 19));
    }
}
