package com.gson.algo.leetcode.top100;

import java.util.*;

/**
 * https://leetcode.cn/problems/top-k-frequent-elements/
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class 前K个高频元素_347 {
    /**
     * 最大堆
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 最大堆
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            heap.offer(entry);
        }
        Map.Entry<Integer, Integer> entry;
        int[] ans = new int[k];
        int idx = 0;
        while ((entry = heap.poll()) != null  && idx < k){
            Integer key = entry.getKey();
            ans[idx++] = key;
        }
        return ans;
    }

    public static void main(String[] args) {
        前K个高频元素_347 solution = new 前K个高频元素_347();
        int[] ans = solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(ans));

        ans = solution.topKFrequent(new int[]{1}, 1);
        System.out.println(Arrays.toString(ans));
    }
}
