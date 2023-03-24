package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/string-compression/
 */
public class 压缩字符串 {

    // 要记住修改原来数组
    public int compress(char[] chars) {
        int ans = 0;
        char preLetter = chars[0];
        int preCount = 1;
        int lastUpdatePos = 0;
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c == preLetter){
                preCount++;
            }else {
                if (preCount == 1){
                    ans++;
                    chars[lastUpdatePos++] = preLetter;
                }else {
                    String preCountStr = (preCount + "");
                    ans +=  preCountStr.length() + 1;
                    chars[lastUpdatePos++] = preLetter;
                    for (int j = 0; j < preCountStr.length(); j++) {
                        chars[lastUpdatePos++] = preCountStr.charAt(j);
                    }
                }
                preLetter = c;
                preCount = 1;
            }
        }
        if (preCount == 1){
            ans++;
            chars[lastUpdatePos++] = preLetter;
        }else {
            String preCountStr = (preCount + "");
            ans +=  preCountStr.length() + 1;
            chars[lastUpdatePos++] = preLetter;
            for (int j = 0; j < preCountStr.length(); j++) {
                chars[lastUpdatePos++] = preCountStr.charAt(j);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        压缩字符串 solution = new 压缩字符串();
        System.out.println(solution.compress(new char[]{'a','a','b','b','c','c','c'}));
        System.out.println(solution.compress(new char[]{'a'}));
        System.out.println(solution.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }
}
