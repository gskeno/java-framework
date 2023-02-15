package com.gson.algo.leetcode.str;

import java.util.Arrays;

/**
 *
 */
public class 求一个字符串的最大公共前后缀II {
    /**
     * 返回一个数组, next[]，长度为S.length
     * next[i]表示字符串S0到Si的最长公共前后缀
     * @param S
     * @return
     */
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

    public int[] getNext1(String s){
        int length = s.length();
        int[] next = new int[length];
        next[0] = 0;
        for (int i = 1; i < length; i++) {
            for (int len = 1; len <= i ; len++) {
                if (s.substring(0, len).equals(s.substring( i +1 -len, i+1))){
                    next[i] = len;
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
        求一个字符串的最大公共前后缀II solution = new 求一个字符串的最大公共前后缀II();
        int[] next;
        next = solution.getNext("abababca");
        System.out.println(Arrays.toString(next));
        System.out.println("----" + Arrays.toString(solution.getNext1("abababca")));

        next = solution.getNext("aabaaf");
        System.out.println(Arrays.toString(next));
    }
}
