package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class 无重复字符的最长子串_3 {

    /**
     * 滑动窗口(左窗口滑动，每找到一个s[i]==s[j]，则maxLen = max{maxLen, j-i},
     * 此后s[i]前面的数字将不再考虑
     * abcdbd
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int maxLen = 0;
        int leftBorder = 0;
        for (int j = 0; j < len; j++) {
            for (int i = leftBorder; i < j; i++) {
                if (chars[j] == chars[i]){
                    maxLen = Math.max(j - i, maxLen);
                    leftBorder = i+1;
                    break;
                }

                if (i == j-1){
                    maxLen = Math.max(j - leftBorder + 1, maxLen);
                }
            }
        }
        return Math.max(maxLen, 1);
    }

    public static void main(String[] args) {
        无重复字符的最长子串_3 solution = new 无重复字符的最长子串_3();
        System.out.println(solution.lengthOfLongestSubstring("abcde"));
        System.out.println(solution.lengthOfLongestSubstring("q"));
        System.out.println(solution.lengthOfLongestSubstring(""));
        System.out.println(solution.lengthOfLongestSubstring("abcdb"));
        System.out.println(solution.lengthOfLongestSubstring("qqqqq"));
        System.out.println(solution.lengthOfLongestSubstring("abcdbd"));
        System.out.println(solution.lengthOfLongestSubstring("abcdbef"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
