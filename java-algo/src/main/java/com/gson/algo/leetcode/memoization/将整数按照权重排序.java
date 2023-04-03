package com.gson.algo.leetcode.memoization;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * https://leetcode.cn/problems/sort-integers-by-the-power-value/
 */
public class 将整数按照权重排序 {

    private Map<Integer, Integer> cache = new HashMap<>();
    public int getKth(int lo, int hi, int k) {
        cache.clear();;
        int n = hi - lo + 1;
        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = getWeight(lo + i);
        }
        Object[] nums = IntStream.rangeClosed(lo, hi ).boxed().toArray();
        Arrays.sort(nums, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                int i1 = Integer.parseInt(o1.toString());
                int i2 = Integer.parseInt(o2.toString());
                if (weight[i1 - lo] != weight[i2 - lo]){
                    return weight[i1 - lo ] - weight[i2 - lo];
                }
                return i1 - i2;
            }
        });
        return Integer.parseInt(nums[k-1].toString());
    }

    /**
     * 获取value的权重值
     * @param value
     * @return
     */
    public int getWeight(int value){

        if (cache.get(value) != null){
            return cache.get(value);
        }
        int weight;
        if (value == 1){
            weight =  0;
        }else if ((value & 1) == 1){
            weight =  1 + getWeight(3*value +1);
        }else {
            weight = 1 + getWeight(value /2);
        }
        cache.put(value, weight);
        return weight;
    }

    public static void main(String[] args) {
        将整数按照权重排序 solution = new 将整数按照权重排序();
        int ans = solution.getKth(12, 15, 2);
        System.out.println(ans);
        ans = solution.getKth(7, 11, 4);
        System.out.println(ans);
    }
}
