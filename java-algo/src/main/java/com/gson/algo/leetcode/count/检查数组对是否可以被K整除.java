package com.gson.algo.leetcode.count;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k/
 */
public class 检查数组对是否可以被K整除 {

    /**
     * [1,2,3,4,5,10,6,7,8,9], k =5， 取模后
     * [1,2,3,4,0,0,1,2,3,4] 排序
     * [0,0,1,1,2,2,3,3,4,4]
     *
     * 
     *
     * @param arr
     * @param k
     * @return
     */
    public boolean canArrange(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % k;
        }
        Arrays.sort(arr);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(-8 % 5);
        System.out.println(28 % 5);
    }
}
