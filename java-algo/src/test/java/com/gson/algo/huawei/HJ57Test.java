package com.gson.algo.huawei;

import org.junit.Test;

public class HJ57Test {
    @Test
    public void test(){
        System.out.println(HJ57高精度整数加法.getSum("7", "5"));
    }

    @Test
    public void test1(){
        System.out.println(HJ57高精度整数加法.getSum("17", "5"));
    }

    @Test
    public void test2(){
        System.out.println(HJ57高精度整数加法.getSum("97", "5"));
    }

    @Test
    public void test3(){
        System.out.println(HJ57高精度整数加法.getSum("997", "50"));
    }
}
