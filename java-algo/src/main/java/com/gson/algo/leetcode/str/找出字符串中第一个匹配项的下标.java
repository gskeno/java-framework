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

    public int strStr1(String haystack, String needle) {
        int i = 0, j = 0;
        int m = haystack.length();
        int n = needle.length();
        int[] next = getNext1(needle);
        while (i < m && j < n){
            if (haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else if ( j != 0){
                j = next[j-1];
            }else {
                i++;
            }
        }
        if ( j == n){
            return i - j;
        }
        return -1;
    }

    public int[] getNext1(String S){
       int len = S.length();
       int[] next = new int[len];
       next[0] = 0;
       int x = 1;
       // 公共前后缀长度
       int preSuffixLen = 0;
       while (x < len){
            // 尝试将S[x] 与 S[preSuffixLen] 对齐，进行相等性比较，在这之前的字符已经是相同的了
            // 为每一个x设置next[x] (即最长公共前后缀长度)
            if (S.charAt(x) == S.charAt(preSuffixLen)){
                preSuffixLen++;
                next[x] = preSuffixLen;
                x++;
            }else if (preSuffixLen != 0){
                preSuffixLen = next[preSuffixLen - 1];
            }
            // preSuffixLen = 0 且S[x] != S[0], 表示S首位与S[x]都不匹配，则从x+1位置再尝试匹配
            else {
                // preSuffixLen = 0;
                // next[x] = 0;
                x++;
            }
       }
       return next;
    }

    public static void main(String[] args) {
        找出字符串中第一个匹配项的下标 solution = new 找出字符串中第一个匹配项的下标();
        int ans;

        ans = solution.strStr("sadbutsad", "sad");
        System.out.println(ans);
        ans = solution.strStr1("sadbutsad", "sad");
        System.out.println(ans);

        ans = solution.strStr("leetcode", "leeto");
        System.out.println(ans);
        ans = solution.strStr1("leetcode", "leeto");
        System.out.println(ans);


    }
}
