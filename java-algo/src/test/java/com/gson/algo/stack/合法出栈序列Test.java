package com.gson.algo.stack;

import org.junit.Test;

public class 合法出栈序列Test {
    @Test
    public void test(){
        int[] nums = new int[]{1,2,3};
        System.out.println(合法出栈序列.legalPop(nums, "321"));
        System.out.println(合法出栈序列.legalPop(nums, "312"));
    }
}
