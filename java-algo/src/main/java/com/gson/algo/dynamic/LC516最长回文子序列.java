package com.gson.algo.dynamic;

/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 *
 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 */
public class LC516最长回文子序列 {

    /**
     * 设置dp[i,j]表示从字符s[i]到字符s[j]所对应的子串的最长回文子序列的长度。
     * 很明显，存在约束 0<=i<=j<=s.length-1。
     *
     * 且i=j时, dp[i][j]=1,因为一个字符肯定时回文子序列
     *
     * 当s[i]=s[j]时，dp[i][j]=dp[i+1][j-1]+2, 因为最左侧和最右侧字符相等了，最长回文子序列的长度依赖s[i+1]到s[j-1]对应子串的最长回文子序列的长度
     *
     * 当s[i]!=s[j]时,dp[i][j] = max(dp[i][j-1], dp[i+1,j])
     *
     * 注意到i依赖i+1, j依赖j-1,所以循环体中是 i--, j++
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        for (int i = len-1; i >= 0 ; i--) {
            for (int j = i; j <= len-1; j++) {
                if (i == j){
                    dp[i][j] = 1;
                }else if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][len-1];
    }


    public static void main(String[] args) {
        LC516最长回文子序列 solution = new LC516最长回文子序列();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
        System.out.println(solution.longestPalindromeSubseq("cbbd"));
    }
}
