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
        int pre = 0; int ans = nums[0];
        for(int n : nums){
            pre = Math.max(pre + n, n);
            ans = Math.max(ans, pre);
        }
        return ans;
    }
}
