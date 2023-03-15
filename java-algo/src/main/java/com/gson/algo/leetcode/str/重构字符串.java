package com.gson.algo.leetcode.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/reorganize-string/
 */
public class 重构字符串 {

    /**
     * 桶思想: 假设s中出现频次最大的字母出现频次为x，则设立x个桶
     * @param s
     * @return
     */
    public String reorganizeString(String s) {
        int[] letterNum = new int[26];
        int maxLetter = -1;
        int  maxLetterNum = 0;
        for(char c : s.toCharArray()){
            letterNum[c - 'a']++;
            if (maxLetter == -1){
                maxLetter = c;
                maxLetterNum = 1;
            }else if (letterNum[c - 'a'] > maxLetterNum){
                maxLetter = c;
                maxLetterNum = letterNum[c - 'a'];
            }
        }
        List<Integer>[] buckets = new List[maxLetterNum];
        for (int i = 0; i < maxLetterNum; i++) {
            buckets[i] = new ArrayList<>();
            buckets[i].add(maxLetter);
        }

        int pos = 0;
        for (int i= 0; i < 26; i++) {
            if (i + 'a' == maxLetter){
                continue;
            }
            while (letterNum[i] != 0){
                // 当前字母出现次数-1
                buckets[pos % buckets.length].add(i + 'a');
                letterNum[i]--;
                pos++;
            }
        }

        int singeBuckets = 0;
        for (int i = buckets.length - 1; i >=0 ; i--) {
            if (buckets[i].size() == 1){
                singeBuckets++;
            }
            if (singeBuckets >= 2){
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buckets.length; i++) {
            for (int val : buckets[i]) {
                sb.append((char)val);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        重构字符串 solution = new 重构字符串();
        System.out.println(solution.reorganizeString("zhmyo"));
    }
}
