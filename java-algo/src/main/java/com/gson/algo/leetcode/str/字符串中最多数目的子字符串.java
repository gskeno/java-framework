package com.gson.algo.leetcode.str;

/**
 *
 */
public class 字符串中最多数目的子字符串 {

    public long maximumSubsequenceCount(String text, String pattern){
        char pattern0 = pattern.charAt(0);
        char pattern1 = pattern.charAt(1);
        int n = text.length();
        int pattern0OccurTimes = 0;
        int pattern1OccurTimes = 0;
        long ans = 0;
        for (int i = n-1; i >= 0 ; i--) {
            if (text.charAt(i) == pattern0){
                pattern0OccurTimes++;
                if (i != n-1){
                    ans += pattern1OccurTimes;
                }
            }

            if (text.charAt(i) == pattern1){
                pattern1OccurTimes++;
            }

        }
        ans += Math.max(pattern0OccurTimes, pattern1OccurTimes);
        return ans;
    }

    public static void main(String[] args) {
        字符串中最多数目的子字符串 solution = new 字符串中最多数目的子字符串();
        System.out.println(solution.maximumSubsequenceCount("aaaa","aa"));
        System.out.println(solution.maximumSubsequenceCount("aabaa","aa"));
        System.out.println(solution.maximumSubsequenceCount("abdcdbc","ac"));
        System.out.println(solution.maximumSubsequenceCount("aabb","ab"));

        System.out.println( 4*2%3);
    }
}
