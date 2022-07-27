package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/minimum-path-sum/
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 */
public class 最小路径和_64 {

    /**
     * 设置dp[i,j]表示从左上角走到grid[i][j]的最小路径和
     * 则有
     * 1. dp[0][0] = grid[0][0]
     * 2. dp[i,0] = dp[i-1,0] + grid[i][0], 当i>0时
     * 3. dp[0,j] = dp[0,j-1] + grid[0][j], 当j>0时
     * 4. dp[i][j] = min{dp[i-1][j], dp[i][j-1]} + grid[i][j]
     * 5. 答案就是dp[lines-1][cols-1]
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int lines = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[lines][cols];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < lines; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < lines; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[lines - 1][cols - 1];
    }
}
