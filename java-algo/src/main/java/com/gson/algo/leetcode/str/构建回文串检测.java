package com.gson.algo.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/can-make-palindrome-from-substring/
 */
public class 构建回文串检测 {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> list = new ArrayList<>();
        // 26个字母出现的次数(是偶数次还是奇数次)
        int[][] letterOddEven = new int[26][s.length()];
        letterOddEven[s.charAt(0) - 'a'][0] = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < 26; j++) {
                if (c - 'a' != j){
                    letterOddEven[j][i] = letterOddEven[j][i-1];
                }else {
                    letterOddEven[j][i] = letterOddEven[j][i-1] == 0 ? 1: 0;
                }
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int k = query[2];

            // 剪枝
            if ( 2*k >= end - start + 1){
                list.add(true);
                continue;
            }

            // 奇数字符出现的次数
            int sum = 0;
            for (int j = 0; j < 26; j++) {
                sum += Math.abs(letterOddEven[j][end] - (start == 0? 0 : letterOddEven[j][start-1]));
            }
            // 子串长度为偶数
            if ( ((end - start) & 1) == 1){
                list.add( 2 * k >= sum);
            }else {
                list.add( 2 * k + 1 >= sum);
            }
        }
        return list;
    }

    public List<Boolean> canMakePaliQueries1(String s, int[][] queries) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int k = query[2];
            boolean b = paliQueries(s.substring(start, end + 1), k);
            list.add(b);
        }
        return list;
    }

    private boolean paliQueries(String s, int k){
        // 剪枝
        if ( 2*k >= s.length()){
            return true;
        }
        int[] alphas = new int[26];
        // 出现奇数次的字符个数
        int ans = 0;
        for(char c: s.toCharArray()){
            if (alphas[c - 'a'] == 0){
                alphas[c - 'a'] = 1;
                ans++;
            }else if (alphas[c - 'a'] == 1){
                alphas[c - 'a'] = 0;
                ans--;
            }
        }
        if (s.length() % 2  == 1){
            return 2*k >= ans - 1;
        }else {
            return 2*k >= ans;
        }
    }

    public static void main(String[] args) {
        构建回文串检测 solution = new 构建回文串检测();
        System.out.println(solution.canMakePaliQueries("lyb", new int[][]{
                        {0, 1, 0},
                        {2, 2, 1}
                }
        ));
        System.out.println(solution.paliQueries("abcd", 1));
        System.out.println(solution.paliQueries("abcd", 2));
        System.out.println(solution.paliQueries("abcda", 1));
        System.out.println(solution.canMakePaliQueries("abcda", new int[][]{
                        {3, 3, 0},
                        {1, 2, 0},
                        {0, 3, 1},
                        {0, 3, 2},
                        {0, 4, 1}
                }
        ));
    }
}
