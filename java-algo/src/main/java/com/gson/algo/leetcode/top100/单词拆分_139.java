package com.gson.algo.leetcode.top100;

import java.util.Arrays;
import java.util.List;

public class 单词拆分_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, 0, wordDict);
    }

    /**
     * s从beginPos位置起，是否与wordDict有匹配的可能性
     * @param s
     * @param beginPos
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, int beginPos, List<String> wordDict){
        // 说明已经完成了一个匹配
        if (beginPos == s.length()){
            return true;
        }
        boolean match = false;
        for(String word : wordDict){
            if (wordBreak(s, beginPos, word)){
                match |= wordBreak(s, beginPos + word.length(), wordDict);
            }
        }
        return match;
    }

    /**
     * 从s的beginPos位置起的子串是否与word匹配
     * @param s
     * @param beginPos
     * @param word
     * @return
     */
    public boolean wordBreak(String s, int beginPos, String word){
        if (beginPos >= s.length()){
            return false;
        }
        int len = word.length();
        if (beginPos + len -1 >= s.length()){
            return false;
        }

        String sub = s.substring(beginPos, beginPos + len);
        return word.equals(sub);
    }

    public static void main(String[] args) {
        单词拆分_139 solution = new 单词拆分_139();
        System.out.println(solution.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(solution.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }
}
