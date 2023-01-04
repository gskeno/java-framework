package com.gson.algo.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring/
 * 给你一个字符串s ，请你返回满足以下条件且出现次数最大的任意子串的出现次数：
 *
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于minSize 且小于等于maxSize 。
 * 
 *
 * 示例 1：
 *
 * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * 输出：2
 * 解释：子串 "aab" 在原字符串中出现了 2 次。
 * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 * 示例 2：
 *
 * 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * 输出：2
 * 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 * 示例 3：
 *
 * 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * 输出：3
 * 示例 4：
 *
 * 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * 输出：0
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s只包含小写英文字母。
 *
 */
public class 子串的最大出现次数 {

    /**
     * 直接暴力，从左到右遍历，对于i开始的子串，子串右边界为 i + minSize - 1到 i + maxSize - 1范围内
     * 判断子串是否满足不同字母数目<=maxLetters的限制
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        int ans = 0;
        Map<String, Integer> occurTimes = new HashMap<>();
        for (int left = 0; left < n; left++) {
            int minRight = left + minSize - 1;
            int maxRight = left + maxSize - 1;
            for (int right = minRight; right <= maxRight ; right++) {
                if (right >= n){
                    continue;
                }
                String sub = s.substring(left, right + 1);
                if (lettersOver(sub, maxLetters)){
                    continue;
                }
                int newOccurTimes = occurTimes.getOrDefault(sub, 0) + 1;
                occurTimes.put(sub, newOccurTimes);
                ans = Math.max(ans, newOccurTimes);
            }
        }
        return ans;
    }

    /**
     * str中字符的个数不能超过limit
     * @param str
     * @param limit
     * @return
     */
    private boolean lettersOver(String str, int limit){
        int[] nums = new int[26];
        int ones = 0;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (nums[idx] == 0){
                nums[idx] = 1;
                ones ++;
            }
        }
        return ones > limit;
    }

    public static void main(String[] args) {
        子串的最大出现次数 solution = new 子串的最大出现次数();
        int ans;
        ans = solution.maxFreq("aababcaab", 2, 3,4);
        System.out.println(ans);

        ans = solution.maxFreq("aaaa", 1, 3,3);
        System.out.println(ans);

        ans = solution.maxFreq("aabcabcab", 2, 2,3);
        System.out.println(ans);

        ans = solution.maxFreq("abcde", 2, 3,3);
        System.out.println(ans);
    }
}
