package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/rotate-array/
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 */
public class 轮转数组 {

    /**
     * 通过观察，能发现事实
     * 1. 将数组nums进行翻转
     * 2. 再将下标0到k-1的元素翻转
     * 3. 再将下标k到n-1的元素翻转
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        flip(nums, 0, nums.length - 1);
        flip(nums, 0, k-1);
        flip(nums, k, nums.length-1);
    }

    private void flip(int[] nums, int startIdx, int endIdx){
        while (startIdx < endIdx){
            int temp = nums[startIdx];
            nums[startIdx] = nums[endIdx];
            nums[endIdx] = temp;
            endIdx--;
            startIdx++;
        }
    }

    public static void main(String[] args) {
        轮转数组 solution = new 轮转数组();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));

    }
}
