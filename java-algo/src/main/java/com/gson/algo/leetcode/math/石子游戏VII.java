package com.gson.algo.leetcode.math;

/**
 * https://leetcode.cn/problems/stone-game-vii/
 */
public class 石子游戏VII {

    /**
     * 设置dp[i][j]，表示 剩余石子范围i~j, 当前轮次中先手操作后，(先手的得分 - 后手的得分)的最大值。先手可能是A，有可能是B
     *
     * 本题，对于A,B来说，到自身局时，都是力争求得 (自己得分-对手得分)最大值，这才是自身的选择方案。
     *
     * i >= j，dp[i][j]明显为0。
     * 当 j - i = 1时，当前用户肯定选择价值小的石头，这样自己的得分高，且对方下一步不得分。
     * 当 j - i > 1时，当前用户可以选择石子i,也可以选择石子j。
     *                  当选择石子i时，当前用户得分sum(i+1到j), 对方下一步得分会领先自己dp[i+1,j]，
     *                  则当前用户本轮会领先对手sum(i+1到j)-dp[i+1,j]。
     *
     *                  当选择石子j时，分析类似。
     *
     *                  当前用户从这两个选择中，选择表现最好的那个
     *
     *   dp[i][j] = max( sum(i+1,j) - dp[i+1][j], sum(i,j-1) - dp[i,j-1]
     *   遍历过程中，j是自增的，i是自减的
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n+1];
        // preSum[i]表示前i个石子的和。(i>0)
        for (int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + stones[i];
        }
        int[][] dp = new int[n][n];
        // i自减，j自增
        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                dp[i][j] = Math.max(preSum[j+1] - preSum[i+1] - dp[i+1][j], preSum[j] - preSum[i] - dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }
}
