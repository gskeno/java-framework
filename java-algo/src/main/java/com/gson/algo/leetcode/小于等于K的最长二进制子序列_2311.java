package com.gson.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k/
 */
public class 小于等于K的最长二进制子序列_2311 {
    public int longestSubsequence(String s, int k) {
        String K = Integer.toBinaryString(k);
        if (K.length() > s.length()) {
            return s.length();
        }
        // key位置(包含)前导0的个数
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, s.charAt(0) == '0' ? 1 : 0);
        for (int i = 1; i < s.length(); i++) {
            cache.put(i, cache.get(i - 1) + (s.charAt(i) == '0' ? 1 : 0));
        }
        int end = s.length() - K.length();
        // 尝试取s的长度为K.length的后缀
        String sub = s.substring(end, end + K.length());
        if (Integer.valueOf(sub, 2) <= k) {
            return cache.get(end - 1) + K.length();
        }
        // 不满足，则取取s的长度为K.length-1的后缀,肯定满足
        return cache.get(end) + (K.length() -1);
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(11713332));
        System.out.println(Integer.toBinaryString(11713332).length());
        小于等于K的最长二进制子序列_2311 solution = new 小于等于K的最长二进制子序列_2311();
        System.out.println(solution.longestSubsequence("1001010", 5));
        System.out.println(solution.longestSubsequence("00101001", 1));
        System.out.println(solution.longestSubsequence("111100010000011101001110001111000000001011101111111110111000011111011000010101110100110110001111001001011001010011010000011111101001101000000101101001110110000111101011000101"
                , 11713332));
    }
}
