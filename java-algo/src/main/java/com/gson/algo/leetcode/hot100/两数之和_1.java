package com.gson.algo.leetcode.hot100;

/**
 *
 * https://leetcode.cn/problems/two-sum/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 */
public class 两数之和_1 {

    public int[] twoSum(int[] nums, int target) {
        // 枚举法，第一个选择的数字下标是i,第二个选择的数字下标是j,
        // i<j<nums.length
        for (int i = 0; i < nums.length; i++) {
            int selected = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] == target - selected){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
}
