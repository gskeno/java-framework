package com.gson.algo.leetcode.count;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/make-number-of-distinct-characters-equal/
 */
public class 使字符串总不同字符的数目相等 {

    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(char c : word1.toCharArray()){
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for(char c : word2.toCharArray()){
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry1 : map1.entrySet()){
            Character key1 = entry1.getKey();
            Integer value1 = entry1.getValue();
            for(Map.Entry<Character, Integer> entry2 : map2.entrySet()){
                Character key2 = entry2.getKey();
                Integer value2 = entry2.getValue();
                // key1和key2相同时
                if (key1 == key2 ){
                   if (map1.keySet().size() == map2.keySet().size()){
                       return true;
                   }
                }
                // key1和key2不同时
                else if (map1.keySet().size() - (value1 > 1 ? 0 : 1) + (map1.containsKey(key2) ? 0 : 1)
                        == map2.keySet().size() - (value2 > 1 ? 0 : 1) + (map2.containsKey(key1) ? 0 : 1)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        使字符串总不同字符的数目相等 solution = new 使字符串总不同字符的数目相等();
        System.out.println(solution.isItPossible("ac", "b"));
        System.out.println(solution.isItPossible("abcc", "aab"));
        System.out.println(solution.isItPossible("abcde", "fghij"));
        System.out.println(solution.isItPossible("aa", "ab"));
    }
}
