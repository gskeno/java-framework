package com.gson.algo.leetcode.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
 */
public class 查找和最小的K对数字 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 大顶堆 [元素1，元素2，元素1+元素2]
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                int sum = nums1[i] + nums2[j];
                if (queue.size() < k) {
                    queue.offer(new int[]{nums1[i], nums2[j], sum});
                    continue;
                }
                if (sum < queue.peek()[2]) {
                    queue.poll();
                    queue.offer(new int[]{nums1[i], nums2[j], sum});
                }
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            List<Integer> pair = new ArrayList<>();
            pair.add(element[0]);
            pair.add(element[1]);
            ret.add(pair);
        }
        return ret;
    }

    public static void main(String[] args) {
        查找和最小的K对数字 solution = new 查找和最小的K对数字();
        List<List<Integer>> lists = solution.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
        System.out.println(lists);

        lists = solution.kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2);
        System.out.println(lists);

        lists = solution.kSmallestPairs(new int[]{1,  2}, new int[]{ 3}, 3);
        System.out.println(lists);
    }
}
