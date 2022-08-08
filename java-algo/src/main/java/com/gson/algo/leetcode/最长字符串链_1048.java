package com.gson.algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-string-chain/
 * @author ruchen
 * @date 2022/8/6
 */
public class 最长字符串链_1048 {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        Map<String, Integer> word2Len = new HashMap<>();
        int max = 1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char[] chars = word.toCharArray();
            if (word.length() == 1){
                word2Len.put(word, 1);
            }else {
                int len = word.length();
                // 移除j位置字符
                for (int j = 0; j < len; j++) {
                    String pre = String.copyValueOf(chars, 0, j).concat(String.copyValueOf(chars, j + 1, len - j - 1));
                    Integer preCount = word2Len.getOrDefault(pre, 0);
                    Integer curCount = preCount + 1;
                    if (word2Len.getOrDefault(word, 0) < curCount){
                        word2Len.put(word, curCount);
                        max = Math.max(max, curCount);
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        最长字符串链_1048 solution = new 最长字符串链_1048();
//        String s = "abcd";
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < s.length(); i++) {
//            String pre = String.copyValueOf(chars, 0, i).
//                    concat(String.copyValueOf(chars, i + 1, s.length()- i - 1));
//            System.out.println(pre);
//        }
//        int max = solution.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
//        System.out.println(max);
        test1();
        test2();
        test3();

    }

    public static void test1(){
        最长字符串链_1048 solution = new 最长字符串链_1048();
        int max = solution.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
        System.out.println(max);
    }

    public static void test2(){
        最长字符串链_1048 solution = new 最长字符串链_1048();
        int max = solution.longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"});
        System.out.println(max);
    }

    public static void test3(){
        最长字符串链_1048 solution = new 最长字符串链_1048();
        int max = solution.longestStrChain(new String[]{"abcd","dbqca"});
        System.out.println(max);
    }
}
