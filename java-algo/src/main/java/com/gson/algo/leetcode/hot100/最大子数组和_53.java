package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/maximum-subarray/
 */
public class 最大子数组和_53 {


    /**
     * 设置f(i)表示以nums[i]结尾的任一子数组的 最大和
     *
     * 则f(i) = max(f(i-1) + nums[i], nums[i])
     * 即nums[i]是另起一段，还是追加到f(i-1)上。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // 以nums[i]结尾的最大子数组和
        int pre = 0;
        int ans = nums[0];
        for(int n : nums){
            pre = Math.max(pre + n, n);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    public int maxSubArray1(int[] nums) {
        // 以当前元素为结束元素的子数组最大和
        int maxEndingHere = nums[0];
        // 目前遇到的子数组的最大和
        int maxSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            maxEndingHere = Math.max(num, num + maxEndingHere);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
