package com.gson.algo.str;

import java.util.HashMap;

/**
 * https://www.nowcoder.com/practice/48d2ff79b8564c40a50fa79f9d5fa9c7
 * 最长不含重复字符的子字符串
 */
public class LongestUnRepeateSubStr {
    // "pwwkew"
    public int lengthOfLongestSubstring (String s) {
        int maxLen = 0;
        int start = -1;
        HashMap<Character,Integer> map = new HashMap<>();
        // 遍历字符串
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if (map.containsKey(cur) && map.get(cur) > start){
                start = map.get(cur);
            }

            map.put(cur, i);
            maxLen = Math.max(maxLen, i - start);

        }
        // 返回最大长度
        return maxLen;
    }


}
