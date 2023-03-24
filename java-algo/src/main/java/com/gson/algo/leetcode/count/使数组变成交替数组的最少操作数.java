package com.gson.algo.leetcode.count;

import java.util.*;

/**
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating/
 */
public class 使数组变成交替数组的最少操作数 {

    public int minimumOperations(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        if (nums.length == 2){
            return nums[0] == nums[1] ? 1: 0;
        }
        // 偶数位置上出现次数最多和次多的元素，以及出现次数
        Map<Integer, Integer> evenCount = new HashMap<>();
        // 奇数位置上出现次数最多和次多的元素，以及出现次数
        Map<Integer, Integer> oddCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i % 2 == 0) {
                evenCount.put(num, evenCount.getOrDefault(num, 0) + 1);
            } else {
                oddCount.put(num, oddCount.getOrDefault(num, 0) + 1);
            }
        }
        //大顶堆
        Set<Map.Entry<Integer, Integer>> evenEntries = evenCount.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> evenQueue = new PriorityQueue<>(2, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        evenQueue.addAll(evenEntries);


        Set<Map.Entry<Integer, Integer>> oddEntries = oddCount.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> oddQueue = new PriorityQueue<>(2, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        oddQueue.addAll(oddEntries);

        Map.Entry<Integer, Integer> pollEven = evenQueue.poll();
        Map.Entry<Integer, Integer> pollOdd = oddQueue.poll();
        if (pollEven.getKey().intValue() != pollOdd.getKey().intValue()) {
            return nums.length - pollEven.getValue() - pollOdd.getValue();
        } else {
            int ans = nums.length;
            // 偶数位置选择最大相同数字，则奇数位置选择次大数字
            if (oddQueue.isEmpty()){
                ans = Math.min(ans, nums.length - pollEven.getValue());
            }else if (!oddQueue.isEmpty()){
                ans = Math.min(ans, nums.length - pollEven.getValue() - oddQueue.peek().getValue());
            }

            // 奇数位置选择最大相同数字，则偶数位置选择次大数字
            if (evenQueue.isEmpty()){
                ans = Math.min(ans, nums.length - pollOdd.getValue());
            }else if (!evenQueue.isEmpty()){
                ans = Math.min(ans, nums.length - pollOdd.getValue() - evenQueue.peek().getValue());
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        使数组变成交替数组的最少操作数 solution = new 使数组变成交替数组的最少操作数();
        System.out.println(solution.minimumOperations(new int[]{48,38,42,18,13,1,97,88,82,48,54,16,78,59,52,30,40,77,59,87,71,28}));
        System.out.println(solution.minimumOperations(new int[]{2,2,2,2,2,2}));
        System.out.println(solution.minimumOperations(new int[]{2,2,2,2,2,2,2}));
        System.out.println(solution.minimumOperations(new int[]{2,4, 2,2, 1,1, 1}));
        System.out.println(solution.minimumOperations(new int[]{3,1,3,2,4,3}));
//        System.out.println(solution.minimumOperations(new int[]{1,2,2,2,2}));
//        System.out.println(solution.minimumOperations(new int[]{4,3, 4,3, 4,3, 3,5, 3,5, 3,3}));
    }
}
