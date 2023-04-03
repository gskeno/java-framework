package com.gson.algo.array;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 */
public class 最小差值平方和 {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(Math.abs(nums1[i] - nums2[i]));
        }
        int k = k1 + k2;
        while (!queue.isEmpty() && k >0){
            Integer num = queue.poll();
            if (num == 0){
                break;
            }
            num--;
            k--;
            queue.offer(num);
        }
        long ans = 0;
        while (!queue.isEmpty()){
            Integer num = queue.poll();
            ans += (long)num * (long)num;
        }
        return ans;
    }

    public static void main(String[] args) {
        最小差值平方和 solution = new 最小差值平方和();
        long ans = 0;
        ans = solution.minSumSquareDiff(new int[]{1, 2, 3, 4}, new int[]{2, 10, 20, 19}, 0, 0);
        System.out.println(ans);

        ans = solution.minSumSquareDiff(new int[]{1,4,10,12}, new int[]{5,8,6,9}, 1, 1);
        System.out.println(ans);
    }
}
