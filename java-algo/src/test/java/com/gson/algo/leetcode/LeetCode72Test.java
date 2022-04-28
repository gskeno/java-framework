package com.gson.algo.leetcode;

import org.junit.Test;

public class LeetCode72Test {
    @Test
    public void test1(){
        String str1 = "1812";
        String str2 = "";
        int distance = 编辑距离72_动态规划.getDistance(str1, str2);
        System.out.println(distance);
    }

    @Test
    public void test2(){
        String str1 = "";
        String str2 = "uo7o1";
        int distance = 编辑距离72_动态规划.getDistance(str1, str2);
        System.out.println(distance);
    }

    @Test
    public void test3(){
        String str1 = "bac";
        String str2 = "aac";
        int distance = 编辑距离72_动态规划.getDistance(str1, str2);
        System.out.println(distance);
    }

    @Test
    public void test4(){
        String str1 = "horse";
        String str2 = "ros";
        int distance = 编辑距离72_动态规划.getDistance(str1, str2);
        System.out.println(distance);
    }
}
