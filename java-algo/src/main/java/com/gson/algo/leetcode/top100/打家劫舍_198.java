package com.gson.algo.leetcode.top100;

public class 打家劫舍_198 {


    /**
     * 设置dp[i]表示从nums[0]到nums[i]所能盗取的最大值
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        if (n == 1) {
            return dp[n - 1];
        }

        dp[1] = Math.max(nums[0], nums[1]);
        if (n == 2) {
            return dp[n - 1];
        }

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }


    /**
     * return rob(nums, 1);
     * 递归会超出时间限制
     *
     * @param nums
     * @param idx
     * @return
     */
    public int rob(int[] nums, int idx) {
        if (idx == nums.length) {
            return nums[idx - 1];
        }

        if (idx > nums.length) {
            return 0;
        }
        // 选择偷第idx家物品
        int res1 = nums[idx - 1] + rob(nums, idx + 2);
        // 不偷第idx家物品
        int res2 = rob(nums, idx + 1);
        return Math.max(res1, res2);
    }
}
