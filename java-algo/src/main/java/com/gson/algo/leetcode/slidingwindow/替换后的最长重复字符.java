package com.gson.algo.leetcode.slidingwindow;

/**
 * https://leetcode.cn/problems/longest-repeating-character-replacement/
 * 
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 *
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 *
 *
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 */
public class 替换后的最长重复字符 {

    /**
     * 提示1: 最长的子串一定需要执行k次替换，除非最长子串与原串相同，不必执行k次
     * 提示2: 左边界固定，如果子串s[l,r]不满足要求，即k次替换仍然不能变为单一字符子串，则s[l,r+?]更长的子串一定也不满足要求
     * 提示3: 左边界固定，如果子串s[l,r]满足要求，即k次替换能变为单一字符子串，s[l,r+?]更长的子串也可能满足要求
     * 提示4: 在寻找更长的符合要求的子字符串过程中，字符串中出现频次最高的字符出现的次数肯定也是更多的
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int left = 0;
        int right = 0;
        int ans = 0;
        // 已经遍历过的窗口中，历史窗口内单一字符出现的最大次数
        int maxCount = 0;
        int[] cs = new int[26];
        while (right < n){
            maxCount = Math.max(maxCount, ++cs[s.charAt(right) - 'A']);
            // k次替换不能使s[left, right]成为单一字符子串
            while (right - left + 1> k + maxCount){
                cs[s.charAt(left) - 'A']--;
                left++;
            }

            ans = Math.max(right - left + 1, ans);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        替换后的最长重复字符 solution = new 替换后的最长重复字符();
        int ans = 0;
        ans = solution.characterReplacement("ABAB", 2);
        System.out.println(ans);

        ans = solution.characterReplacement("AABABBA", 1);
        System.out.println(ans);
    }
}
