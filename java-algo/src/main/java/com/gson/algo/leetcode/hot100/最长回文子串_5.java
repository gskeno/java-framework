package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * "babad"
 * "cbbd"
 */
public class 最长回文子串_5 {

    public String longestPalindrome(String s) {
        return longestPalindrome1(s);
    }

    /**
     * 动态规划
     * 二维数组
     * 设置dp[i][j]表示字符串s从下标位置i到j的子串是否是回文字符串(true or false)
     * 来探索动态规划的线索。
     * 1. dp[i][j]=true, 当i=j时
     * 2. dp[i][j]=true，当dp[i+1][j-1]=true且s[i]=s[j]时
     * <p>
     * 注意到，规划的过程中，i是递减的，j是递增的，且 0<=i<=j<=s.length-1
     * <p>
     * 则dp[i][j]==true的j-i+1最大的s[i]--->s[j]是最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 0;
        int start = 0;
        int end = 0;
        for (int i = s.length() - 1; i > -1; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp[i][j] = true;
                }

                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                } else if (i + 1 < s.length() && j - 1 > -1 && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                } else if (i + 1 < s.length() && j - 1 > -1 && (i + 1 == j)) {
                    dp[i][j] = true;
                }

                if (dp[i][j] && (j - i + 1 > maxLen)) {
                    maxLen = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }


    public static void main(String[] args) {
        最长回文子串_5 solution = new 最长回文子串_5();
        System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindrome("babad"));
    }
}
