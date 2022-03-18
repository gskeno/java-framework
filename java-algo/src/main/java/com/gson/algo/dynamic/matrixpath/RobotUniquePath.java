package com.gson.algo.dynamic.matrixpath;

/**
 * 机器人行走路径，机器从一个m*n的方格的左上顶点走到右下顶点，
 * 有多少种方式。
 *
 * 例如格子的大小是3*3，则有6种方式可以从左上角走到右下角
 */
public class RobotUniquePath {

    public int uniquePath(int m , int n){
        int[][] dp = new int[m][n];
        return helper(m-1, n-1, dp);
    }

    /**
     * f(i,j)表示从(0,0)到达(i,j)的路径的数目
     */
    private int helper(int i, int j, int[][] dp){
        if (dp[i][j] == 0){
            if (i == 0 || j == 0){
                dp[i][j] = 1;
            }else {
                dp[i][j] = helper(i-1, j, dp) + helper(i, j-1, dp);
            }
        }

        return dp[i][j];
    }

    public static void main(String[] args) {
        RobotUniquePath robotUniquePath = new RobotUniquePath();
        int count = robotUniquePath.uniquePath(3, 3);
        System.out.println(count);
    }
}
