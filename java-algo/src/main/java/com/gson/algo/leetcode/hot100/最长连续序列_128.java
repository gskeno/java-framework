package com.gson.algo.leetcode.hot100;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 * 
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 *
 */
public class 最长连续序列_128 {

    /**
     * 使用hash表，遍历一次数组元素，
     * 如果数组元素值为m, 且m-1也在hash表中，则m肯定不是最长连续序列的开头元素，
     * 因为m-1比其更可能是
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums){
            numSet.add(num);
        }
        int longest = 0;
        for(int num : nums){
            // 当num-1不在hash表中，才可能是最长连续序列的开头
            if (!numSet.contains(num-1)){
                int cur = num;
                int len = 1;
                while (numSet.contains(cur+1)){
                    cur++;
                    len++;
                }
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        最长连续序列_128 solution = new 最长连续序列_128();
    }
}
