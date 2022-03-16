package com.gson.algo.dynamic.plan;

/**
 * https://www.nowcoder.com/practice/2237b401eb9347d282310fc1c3adb134
 * 最大礼物
 *
 * 在一个 m * n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
 * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 如输入这样的一个二维数组，
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 那么路径 1→3→5→2→1 可以拿到最多价值的礼物，价值为12
 */
public class MaxGift {

    /**
     * 设dp[i][j]表示从棋盘grid[0][0]到grid[i][j]所能收到的最大礼物
     * 则有 dp[i][j] = max(dp[i-1][j],dp[i][j-1]) + grid[i][j]
     * @param grid
     * @return
     */
    public int maxValue (int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // write code here
        int[][]dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
