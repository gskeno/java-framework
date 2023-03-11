package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/decoded-string-at-index/
 */
public class 索引处的解码字符串 {

    public String decodeAtIndex(String s, int k) {
        long size = 0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                size = size * (c - '0');
            }else {
                size++;
            }
        }

        for (int i = s.length() - 1; i >=0 ; i--) {
            char c = s.charAt(i);
            k = (int)((long)k % size);

            if (k == 0 && Character.isLetter(c)){
                return String.valueOf(c);
            }
            if (Character.isDigit(c)){
                size = size / (c - '0');
            }else {
                size--;
            }
        }
        return "";
    }
}
