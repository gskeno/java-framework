package com.gson.algo.huawei;

import org.junit.Test;

import java.util.Arrays;

public class Hj24Test {
    @Test
    public void  test(){
        String[] heights = {"186", "186", "150", "200", "160", "130", "197", "200"};
        int evictNums = HJ24合唱队.getEvictNums(heights);
        System.out.println(evictNums);
    }

    @Test
    public void test1(){
        String s = "186 186 150 200 160 130 197 200 ";
        String[] s1 = s.split(" ");
        System.out.println(Arrays.toString(s1));
    }
}
