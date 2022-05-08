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

    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(7);
        list.add(7);
        list.add(3);
        boolean flag = HJ67的24点游戏算法.reach24(list);
        System.out.println(flag);
    }


    @Test
    public void test4(){
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(7);
        list.add(1);
        list.add(2);
        boolean flag = HJ67的24点游戏算法.reach24(list);
        System.out.println(flag);
    }

    @Test
    public void test5(){
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(7);
        list.add(1);
        list.add(3);
        boolean flag = HJ67的24点游戏算法.reach24(list);
        System.out.println(flag);
    }

    @Test
    public void test6(){
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(7);
        list.add(1);
        list.add(4);
        boolean flag = HJ67的24点游戏算法.reach24(list);
        System.out.println(flag);
    }




}
