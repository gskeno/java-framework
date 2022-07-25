package com.gson.algo.leetcode.top100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/word-break/
 * <p>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class 单词拆分_139 {
    /**
     * 一般能用递归解决的问题，都可以尝试使用动态规划解决问题
     * <p>
     * 设置dp[i]表示s的前i个字符,s0到s[i-1]能否被拼接出来。
     * 很容易想到，如果dp[i]能被拼接，则存在一个j<i, d[j]也可以被拼接
     * 且s[j]到s[i-1]所构成的字符串是字典中的一个单词
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 最短单词长度
        int minLen = Integer.MAX_VALUE;
        // 最长单词长度
        int maxLen = 0;
        for (String tmp : wordDict) {
            minLen = Math.min(minLen, tmp.length());
            maxLen = Math.max(maxLen, tmp.length());
        }
        Set<String> words = new HashSet<>(wordDict);
        boolean dp[] = new boolean[s.length() + 1];
        // 认为空字符串可以被拼接
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            int leftBorder = i - maxLen;
            int rightBorder = i - minLen;
            if (leftBorder<0){
                leftBorder = 0;
            }
            for (int j = leftBorder; j <= rightBorder; j++) {
                dp[i] = dp[j] & check(s, j, i, words);
                if (dp[i] == true) {
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // 确认s[i]到s[j]构成的字符串是否是words中的一个单词
    public boolean check(String s, int i, int j, Set<String> words) {
        String sub = s.substring(i, j);
        return words.contains(sub);
    }

    /**
     * 递归调用，总是超时
     * s从beginPos位置起，是否与wordDict有匹配的可能性
     *
     * @param s
     * @param beginPos
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, int beginPos, List<String> wordDict) {
        // 说明已经完成了一个匹配
        if (beginPos == s.length()) {
            return true;
        }
        boolean match = false;
        for (String word : wordDict) {
            if (wordBreak(s, beginPos, word)) {
                match |= wordBreak(s, beginPos + word.length(), wordDict);
            }
        }
        return match;
    }

    /**
     * 从s的beginPos位置起的子串是否与word匹配
     *
     * @param s
     * @param beginPos
     * @param word
     * @return
     */
    public boolean wordBreak(String s, int beginPos, String word) {
        if (beginPos >= s.length()) {
            return false;
        }
        int len = word.length();
        if (beginPos + len - 1 >= s.length()) {
            return false;
        }

        String sub = s.substring(beginPos, beginPos + len);
        return word.equals(sub);
    }

    public static void main(String[] args) {
        单词拆分_139 solution = new 单词拆分_139();
        //System.out.println(solution.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(solution.wordBreak("bb", Arrays.asList("a", "b", "bbbb", "bbbb")));
        //System.out.println(solution.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }
}
