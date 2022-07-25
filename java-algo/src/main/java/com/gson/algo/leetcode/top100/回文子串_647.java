package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/palindromic-substrings/
 *
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 *
 * 回文字符串 是正着读和倒过来读一样的字符串。
 *
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 */
public class 回文子串_647 {

    /**
     * 设置dp[i]表示从s0到si所形成的回文子串的数目
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1];
            for (int j = 0; j <= i; j++) {
                if (isHuiwen(s, j, i)){
                    dp[i]++;
                }
            }
        }

        return dp[n-1];
    }

    public boolean isHuiwen(String s, int begin, int end){
        while (begin <= end){
            if (s.charAt(begin) != s.charAt(end)){
                return false;
            }

            begin++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        回文子串_647 solution = new 回文子串_647();
        System.out.println(solution.countSubstrings("aaa"));
        System.out.println(solution.countSubstrings("abc"));
    }
}
