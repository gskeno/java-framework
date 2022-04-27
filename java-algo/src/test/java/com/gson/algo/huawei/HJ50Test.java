package com.gson.algo.huawei;

import org.junit.Test;

public class HJ50Test {
    @Test
    public void test(){
        System.out.println(HJ50四则运算.operate("5+2"));
        System.out.println("-----------------------------");
        System.out.println(HJ50四则运算.operate("5-2"));
        System.out.println("-----------------------------");
        System.out.println(HJ50四则运算.operate("-5-2"));
        System.out.println("-----------------------------");
        System.out.println(HJ50四则运算.operate("-5+2"));
        System.out.println("-----------------------------");
    }

    @Test
    public void test1(){
        System.out.println(HJ50四则运算.operate("(-5+2)"));
        System.out.println("-----------------------------");
    }

    @Test
    public void test2(){
        System.out.println(HJ50四则运算.operate("2+[1+2*3-(5-1)]"));
        System.out.println("-----------------------------");
    }
}
