package com.gson.algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/maximum-length-of-pair-chain/
 *
 * 美妙的贪心
 */
public class 最长数对链 {

    /**
     * 第一个数对怎么选? 哪个数对的第2个值最小，就做为第一个数对被选择。
     * 第二个数对怎么选？排除第一个数对，剩下的数对中，哪个数对的第2个值最小，就做为第二个数对被选择。
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int ans = 1;
        int cur = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > cur){
                ans++;
                cur = pairs[i][1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        最长数对链 solution = new 最长数对链();
        int[][] pairs = {
                {1, 2},
                {2, 3},
                {3, 4}
        };
        int ans = solution.findLongestChain(pairs);
        System.out.println(ans);
    }
}
