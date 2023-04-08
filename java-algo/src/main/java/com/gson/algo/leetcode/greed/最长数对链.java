package com.gson.algo.leetcode.greed;

import java.util.Arrays;
import java.util.Comparator;

public class 最长数对链 {

    public int findLongestChain(int[][] pairs) {
        // 根据right升序，left降序排列
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]){
                    return o1[1] - o2[1];
                }
                return o2[0] - o2[1];
            }
        });
        int curRight = Integer.MIN_VALUE;
        int ret = 0;
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > curRight){
                curRight = pairs[i][1];
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        最长数对链 solution = new 最长数对链();
        System.out.println(solution.findLongestChain(new int[][]{{1,2}, {2,3}, {3,4}}));
        System.out.println(solution.findLongestChain(new int[][]{{1,2}, {3,4}, {2,3}}));
        System.out.println(solution.findLongestChain(new int[][]{{1,2}, {3,4}, {3,4}}));
        System.out.println(solution.findLongestChain(new int[][]{{1,2}, {7,8}, {4,5}}));
        System.out.println(solution.findLongestChain(new int[][]{{1,2}, {7,8}, {4,5}, {6,7},{3,4}, {5,6}, {2,3}, {4,5}}));
    }
}
