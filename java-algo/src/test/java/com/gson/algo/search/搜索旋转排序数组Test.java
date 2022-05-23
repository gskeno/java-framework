package com.gson.algo.search;

import org.junit.Test;

public class 搜索旋转排序数组Test {

    @Test
    public void test(){
        搜索旋转排序数组 solution = new 搜索旋转排序数组();
        System.out.println(solution.search(new int[]{3,1}, 1));
        System.out.println(solution.search(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println(solution.search(new int[]{1}, 0));
    }
}
