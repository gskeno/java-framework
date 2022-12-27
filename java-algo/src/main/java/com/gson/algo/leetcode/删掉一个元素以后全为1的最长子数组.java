package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 *
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 *
 * 如果不存在这样的子数组，请返回 0 。
 *
 */
public class 删掉一个元素以后全为1的最长子数组 {

    /**
     * 维护一个滑动窗口，窗口内最多只有一个元素为0
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int left =0, right = 0;
        int curPosOf0 = -1;
        for (int i = 0; i <n ; i++) {
            if (nums[i] == 1){
                right = i;
            }else if (curPosOf0 == -1){
                right = i;
                curPosOf0 = i;
            }else {
                ans = Math.max(right - left, ans);
                left = curPosOf0 + 1;
                right = i;
                curPosOf0 = i;
            }
        }
        if (curPosOf0 == -1){
            return n -1;
        }
        return  Math.max(right - left, ans);
    }

    public static void main(String[] args) {
        删掉一个元素以后全为1的最长子数组 solution = new 删掉一个元素以后全为1的最长子数组();
        System.out.println(solution.longestSubarray(new int[]{1,1,0,1}));
        System.out.println(solution.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
        System.out.println(solution.longestSubarray(new int[]{1,1,1}));
        System.out.println(solution.longestSubarray(new int[]{0,0,1,1,1,1,0,1,1,0,0}));
    }
}
