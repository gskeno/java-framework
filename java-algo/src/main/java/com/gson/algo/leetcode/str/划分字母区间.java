package com.gson.algo.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/partition-labels/
 */
public class 划分字母区间 {

    /**
     * 1. 记录每个字符的最后一个位置
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        int[] pos = new int[26];
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int right = 0;
        int preRangeLen = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, pos[s.charAt(i) - 'a']);
            if (right == i){
                ans.add( i + 1 - preRangeLen);
                preRangeLen = i+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        划分字母区间 solution = new 划分字母区间();
        List<Integer> list;
        list = solution.partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(list);
        list = solution.partitionLabels("eccbbbbdec");
        System.out.println(list);

    }
}
