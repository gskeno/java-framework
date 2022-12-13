package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/plates-between-candles/
 */
public class 蜡烛之间的盘子 {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        // 盘子数量的前缀和
        int[] preSumPlates = new int[n];
        // 左侧最近的蜡烛位置(如果自己本身就是蜡烛，那么最近位置就是自身位置),不存在，设置为-1
        int[] left = new int[n];
        // 右侧最近的蜡烛位置(如果自己本身就是蜡烛，那么最近位置就是自身位置),不存在，设置为-1
        int[] right = new int[n];
        for (int i = 0, l = -1; i < n; i++) {
            if (s.charAt(i) == '|'){
                l = i;
            }
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >=0 ; i--) {
            if (s.charAt(i) == '|'){
                r = i;
            }
            right[i] = r;
        }

        for (int i = 0, sum = 0; i < n; i++) {
            if (s.charAt(i) == '*'){
                sum++;
            }
            preSumPlates[i] = sum;
        }
        // 上方都是预处理
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int leftCandlePos = right[start];
            int rightCandlePos = left[end];
            if (start <= leftCandlePos && leftCandlePos <= rightCandlePos && rightCandlePos <= end){
                ans[i] = preSumPlates[rightCandlePos] - preSumPlates[leftCandlePos];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        蜡烛之间的盘子 solution = new 蜡烛之间的盘子();
        int[] ans = solution.platesBetweenCandles("***|**|*****|**||**|*", new int[][]{
                {1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}
        });
        System.out.println(Arrays.toString(ans));
    }
}
