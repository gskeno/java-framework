package com.gson.algo.leetcode.top100;

import java.util.*;

/**
 * https://leetcode.cn/problems/group-anagrams/
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class 字母异位词分组_49 {
    /**
     * 首先寻找一组 字母异位词的特征，将其作为key；一组字母异位词，作为value
     * <p>
     * 什么可以作为key呢？
     * 1. 一组字母异位词的字母按照字典排序肯定都是一样的
     * 2. 一组字母异位词的各个字母出现的次数都是一样的。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> values = map.getOrDefault(key, new ArrayList<>());
            values.add(str);
            map.put(key, values);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

    }
}
