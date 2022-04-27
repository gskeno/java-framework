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

    @Test
    public void test3(){
        System.out.println(HJ50四则运算.operate("3+2*{1+2*[-4/(8-6)+7]}"));
    }

    @Test
    public void test4(){
        System.out.println(HJ50四则运算.operate("3*5+8-0*3-6+0+0"));
    }

    @Test
    public void test5(){
        System.out.println(HJ50四则运算.operate("(7+5*4*3+6)"));
    }

    @Test
    public void test6(){
        System.out.println(HJ50四则运算.operate("3-10+(0+(10+5+3)-10)"));
    }
}
