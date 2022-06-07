package com.gson.algo.dynamic;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 */
public class LC1143最长公共子序列 {

    /**
     * 设dp[i][j]表示text1的前i个字符组成的子串(0<=i<=text1.length)
     * 与 text2的前j个字符组成的子串(0<=j<=text2.length)的最长公共子序列的长度。
     *
     * 则容易发现:
     * dp[0][j]=0;因为text1的前0个字符组成的子串为空字符串，与text2的前j个字符组成的子串的最长公共子序列就是空串，长度为0
     *
     * dp[i][0]=0;原因同上
     *
     * 当i,j>0时
     * 如果text1的第i个字符 text1[i-1] 与text2的第j个字符 text2[j-1]相等时, dp[i][j]=dp[i-1][j-1] + 1;
     * 如果不相等,则从dp[i-1][j] 和dp[i][j-1]中找出最大的那一个，即dp[i][j]= max(dp[i-1][j], dp[i][j-1])
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][]dp = new int[len1+1][len2+2];
        for (int i = 1; i <= len1 ; i++) {
            for (int j = 1; j <=len2 ; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        LC1143最长公共子序列 solution = new LC1143最长公共子序列();
        System.out.println(solution.longestCommonSubsequence("abcde","ace"));
        System.out.println(solution.longestCommonSubsequence("abc","abc"));
        System.out.println(solution.longestCommonSubsequence("abc","def"));
    }
}
