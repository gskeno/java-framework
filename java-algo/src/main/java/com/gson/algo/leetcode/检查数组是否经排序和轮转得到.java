package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/
 */
public class 检查数组是否经排序和轮转得到 {

    public boolean check(int[] nums) {
        int downCount = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            downCount++;
            if (downCount >= 2) {
                return false;
            }
        }

        if (downCount == 1){
            return nums[n-1] <= nums[0];
        }

        return true;
    }
}
