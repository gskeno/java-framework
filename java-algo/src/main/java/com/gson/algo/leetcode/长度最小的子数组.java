package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 * 
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 *
 *
 */
public class 长度最小的子数组 {

    /**
     * 滑动窗口 + 双指针
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = 0;
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        while (end < n){
            sum += nums[end];
            // 窗口[start,end]内的元素之和 >= 目标值时，窗口左边界往右滑动1位
            while (sum >= target){
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            // 每轮循环，当窗口内元素之和小于目标值时，窗口右边界往右滑动1位
            end++;
        }
        return ans;
    }

    public static void main(String[] args) {
        长度最小的子数组 solution = new 长度最小的子数组();
        int ans = solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(ans);
    }
}
