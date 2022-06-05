package com.gson.algo.dynamic;

/**
 * https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * 动态规划
 */
public class LC1312让字符串成为回文串的最少插入次数 {
    /**
     * 设置二维数组dp[i,j] 说明，i,j表示字符串s的从i到j的子串(i起始值为0, j最大值为s.length-1)要成为回文串的最少插入次数
     *
     * 1. 当s[i] = s[j]时，dp[i][j]=dp[i+1][j-1]
     * 2. 当s[i] != s[j]时，要么在s[i]的前方插入一个等于s[j]的字符，要么在s[j]的后方插入一个等于s[i]的字符
     * 前者的话,dp[i][j]=dp[i][j-1];
     * 后者的话,dp[i][j]=dp[i+1][j]，
     * 即dp[i][j]= min (dp[i][j-1]+1, dp[i+1][j]+1)
     *
     * 注意到求dp[i][j]时,dp[i+1][j], dp[i][j-1],dp[i+1][j-1]都已经求出，
     * 所以循环体中应该时 i--, j++的样子
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];

        for (int i = length-2; i >=0; i--) {
            for (int j = i+1; j <length ; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][length-1];
    }

    public int minInsertions2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int span = 2; span <= n; ++span) {
            for (int i = 0; i <= n - span; ++i) {
                int j = i + span - 1;
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
                // System.out.println("i=" + i + ",j=" + j);
            }
        }

        return dp[0][n-1];
    }

    public static void main(String[] args) {
        LC1312让字符串成为回文串的最少插入次数 solution = new LC1312让字符串成为回文串的最少插入次数();
        System.out.println(solution.minInsertions2("zzazz"));
        System.out.println(solution.minInsertions2("mbadm"));
        System.out.println(solution.minInsertions2("leetcode"));
    }
}
