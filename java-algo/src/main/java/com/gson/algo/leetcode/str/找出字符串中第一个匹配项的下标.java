package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class 找出字符串中第一个匹配项的下标 {


    /**
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        int n = haystack.length();
        int m = needle.length();
        int[] next = getNext(needle);
        while ( i < n && j < m){
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        if (j == m){
            return i - j;
        }
        return -1;
    }

    public int[] getNext(String S){
        int n = S.length();
        int[] next = new int[n+1];
        next[0] = -1;
        for (int i = 0, j = -1; i < n;) {
            if (j == -1 || S.charAt(i) == S.charAt(j)){
                i++;
                j++;
                next[i] = j;
                System.out.println("next[" + i + "]=" + j);
            }else {
                System.out.println("j=next[" + j + "]=" + next[j]);
                j = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        找出字符串中第一个匹配项的下标 solution = new 找出字符串中第一个匹配项的下标();
        int ans;

        ans = solution.strStr("sadbutsad", "sad");
        System.out.println(ans);

        ans = solution.strStr("leetcode", "leeto");
        System.out.println(ans);
    }
}
