package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/perfect-squares/
 * <p>
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，
 * 其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，
 * 而 3 和 11 不是。
 * <p>
 * 设置dp[i]表示和为i的完全平方数的最小数量
 */
public class 完全平方数_279 {


    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        完全平方数_279 solution = new 完全平方数_279();
        System.out.println(solution.numSquares(1));
        System.out.println(solution.numSquares(2));
        System.out.println(solution.numSquares(3));
        System.out.println(solution.numSquares(4));
        System.out.println(solution.numSquares(5));
        System.out.println(solution.numSquares(6));
        System.out.println(solution.numSquares(7));
        System.out.println(solution.numSquares(8));
    }
}
