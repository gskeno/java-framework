package com.gson.algo.huawei;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HJ67Test {
    @Test
    public void test(){
        int[] nums = new int[]{7, 2, 1, 10};
        int[] visit = new int[]{1, 0 ,0 ,0 };
        int temp = 7;
        boolean dfs = HJ67的24点游戏算法.dfs(nums, visit, temp);
        System.out.println(dfs);
    }

    @Test
    public void test1(){
        int[] nums = new int[]{7, 10, 1, 2};
        int[] visit = new int[]{1, 0 ,0 ,0 };
        int temp = 7;
        boolean dfs = HJ67的24点游戏算法.dfs(nums, visit, temp);
        System.out.println(dfs);
    }

    @Test
    public void test2(){
        int[] nums = new int[]{10, 7, 1, 2};
        int[] visit = new int[]{1, 0 ,0 ,0 };
        int temp = 10;
        boolean dfs = HJ67的24点游戏算法.dfs(nums, visit, temp);
        System.out.println(dfs);
    }




}
