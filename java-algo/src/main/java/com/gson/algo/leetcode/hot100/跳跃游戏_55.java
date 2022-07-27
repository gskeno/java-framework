package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/jump-game/
 */
public class 跳跃游戏_55 {

    /**
     * 设dp[i]表示能否从nums[i]跳跃到终点
     * <p>
     * 从i开始，可以一步不跳，跳1步，跳2步，跳nums[i]步
     * 则dp[i]= (i==n-1) || dp[i+1] || dp[i+2] || dp[i + nums[i]]
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        // 最后一个元素一步不跳都可以到终点
        for (int i = n - 2; i >= 0; i--) {
            int element = nums[i];
            // 最少跳一步，最多跳 n - i - 1步
            for (int j = 1; j < n - i && j <= element; j++) {
                dp[i] |= dp[i + j];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        跳跃游戏_55 solution = new 跳跃游戏_55();
        int[] nums ;
        nums = new int[]{0};
        System.out.println(solution.canJump(nums));

        nums = new int[]{1};
        System.out.println(solution.canJump(nums));

        nums = new int[]{2,3,1,1,4};
        System.out.println(solution.canJump(nums));

        nums = new int[]{3,2,1,0,4};
        System.out.println(solution.canJump(nums));
    }
}
