package com.gson.algo.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组，找出其中最小的K个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，
 * 则最小的4个数字是1,2,3,4。如果K>数组的长度，
 * 那么返回一个空的数组
 */
public class TopKMin {

    /**
     * 最大堆
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || k > input.length) {
            return new ArrayList<>();
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(input[i]);
        }
        for (int i = k; i <= input.length - 1 && queue.peek() != null; i++) {
            if (input[i] < queue.peek()) {
                queue.poll();
                queue.add(input[i]);
            }
        }
        ArrayList<Integer> mins = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            mins.add(queue.poll());
        }
        return mins;
    }

}
