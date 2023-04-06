package com.gson.algo.leetcode.statecompress;

import java.util.Arrays;

/**
 * n个糖果袋子，每个糖果袋子中有若干颗糖果，你可以选择任意个糖果袋子(最低0个，最多n个)，
 * 问 你有多少种选择，每种选择下糖果的个数是多少
 */
public class 子集和 {

    /**
     * @param n n个糖果袋子
     * @param candies 每个糖果袋子种的糖果个数
     */
    public int[] subsetSum(int n, int[] candies){
        // 总共 1 << n个选择
        int M = 1 << n;
        int[] strategies = new int[M];

        // 从小到打遍历 集合，大集合由小集合转化而来
        // 比如 1011 由 0001 + 0010 + 1000 转化而来
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < n ; j++) {
                if ( (1 << j) <= i &&  ((1<<j) & i) != 0){
                    strategies[i] += candies[j];
                }
            }
        }
        return strategies;
    }

    /**
     * @param n n个糖果袋子
     * @param candies 每个糖果袋子种的糖果个数
     */
    public int[] subsetSum1(int n, int[] candies){
        // 总共 1 << n个选择
        int M = 1 << n;
        int[] strategies = new int[M];

        for (int i = 0; i < n; i++) {
            int base = 1 << i;
            // 1xxxx由 10000 | xxxx组成
            for (int j = 0; j < base; j++) {
                strategies[base | j] = candies[i] + strategies[j];
            }
        }
        return strategies;
    }

    public static void main(String[] args) {
        子集和 solution = new 子集和();
        int[] ans = solution.subsetSum(4, new int[]{2, 3, 5, 7});
        int[] ans1 = solution.subsetSum(4, new int[]{2, 3, 5, 7});
        for (int i = 0; i < ans.length; i++) {
            System.out.println(Integer.toBinaryString(i) + "--->" + ans[i]);
            System.out.println(Integer.toBinaryString(i) + "--->" + ans1[i]);
            System.out.println("<<=======>>");
        }
    }
}
