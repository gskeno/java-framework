package com.gson.algo.huawei;

import org.junit.Test;

public class HJ103Test {
    @Test
    public void test(){
        int[] nums = {268, 90 ,179, 129, 204, 224};
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 0, nums[0]));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 1, nums[1]));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 2, nums[2]));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 3, nums[3]));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 4, nums[4]));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 5, nums[5]));
    }

    @Test
    public void test1(){
        int[] nums = {1,2,3,2};
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 0));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 1));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 2));
        System.out.println(HJ103走梅花桩.getMaxSteps(nums, 3));
    }
}
