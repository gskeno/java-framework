package com.gson.algo.sort;

import java.util.*;

/**
 * 求数组中出现频次最多的前K个数
 *
 * 哈希 + 最小堆
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k ){

        // 1. 统计各个元素出现的频次, 元素做key, 出现频次做val
        Map<Integer,Integer> element2Freq = new HashMap<>();
        for(int num : nums){
            if (element2Freq.containsKey(num)){
                element2Freq.put(num, element2Freq.get(num) + 1);
            }else {
                element2Freq.put(num, 1);
            }
        }

        // 2. 对每个键值对entry进行堆排序，构建最小堆，堆元素个数为k，排序规则是对entry中的val进行比较
        // 这样构建的最小堆的堆顶元素就是第出现频次第K大的entry，也是堆中出现频次最小的entry,
        Set<Map.Entry<Integer, Integer>> entries = element2Freq.entrySet();
        PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for(Map.Entry<Integer,Integer> entry : entries){
            // 元素不到k个时，直接填充
            if (minHeap.size() < k){
                minHeap.offer(entry);
            }
            // 新元素出现的频次比堆顶元素出现的次数多，则移出堆顶元素，put新元素进入堆
            else if (minHeap.peek().getValue() < entry.getValue()){
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }

        return result;

    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        int[] nums = {1,2,2,1,3,1};
        int k = 2;
        int[] result = topKFrequent.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }
}
