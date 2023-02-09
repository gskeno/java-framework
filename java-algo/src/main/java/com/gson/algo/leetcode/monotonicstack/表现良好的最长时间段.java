package com.gson.algo.leetcode.monotonicstack;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-well-performing-interval/
 * 
 * 给你一份工作时间表hours，上面记录着某一位员工每天的工作小时数。
 *
 * 我们认为当员工一天中的工作小时数大于8 小时的时候，那么这一天就是「劳累的一天」。
 *
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 *
 * 请你返回「表现良好时间段」的最大长度。
 *
 *
 */
public class 表现良好的最长时间段 {
    /**
     * 提示1: 构建一个数组performances[], 元素只有1(劳累)和-1(不劳累)。
     *       尝试找到最长的区间，且区间内元素和 >0。区间内元素和会使人想到前缀和。
     * 提示2: 维护一个前缀和数组preSum[]，对于下标i, 尝试找到最小的下标j，
     *       使 preSum[i] - preSum[j] >0 (表示j到i区间劳累的天数比不劳累多)
     * 提示3: 这变成了对数组preSum[] 求 最大宽度坡。
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        // 劳累的一天标记为1，不劳累标记为-1
        int[] performances = new int[n];
        for (int i = 0; i < n; i++) {
            performances[i] = hours[i] > 8 ? 1 : -1;
        }
        // 前缀和
        int[] preSum = new int[n+1];
        for (int i = 0; i < n ; i++) {
            preSum[i+1] = preSum[i] + performances[i];
        }
        // 对数组preSum求最大宽度坡
        Integer[] M = new Integer[n+1];
        for (int i = 0; i < M.length; i++) {
            M[i] = i;
        }
        // 下标排序，下标对应的前缀和越小，越排在前面，
        Arrays.sort(M, (o1, o2) -> preSum[o1] - preSum[o2]);
        int ans = 0;
        // 维护一个单调递减栈，栈中的元素 可能是区间的起点
        Deque<Integer> monoStack1 = new LinkedList<>();
        for(int m : M){
            if (monoStack1.isEmpty() || (m < monoStack1.peek() && preSum[m] > preSum[monoStack1.peek()])){
                monoStack1.push(m);
            }
        }
        // 倒序遍历前缀和数组，看其是否可能是一个可能的区间终点
        for (int i = preSum.length - 1; i >=0 ; i--) {
            while (!monoStack1.isEmpty() && preSum[i] > preSum[monoStack1.peekLast()]){
                Integer j = monoStack1.pollLast();
                ans = Math.max(ans, i - j);
            }
            if (monoStack1.isEmpty()){
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        表现良好的最长时间段 solution = new 表现良好的最长时间段();
        int ans = 0;
        ans = solution.longestWPI(new int[]{6,6,9});
        System.out.println(ans);
        ans = solution.longestWPI(new int[]{9,9,6,0,6,6,9});
        System.out.println(ans);
        ans = solution.longestWPI(new int[]{6,6,6});
        System.out.println(ans);
        ans = solution.longestWPI(new int[]{9,9,9});
        System.out.println(ans);
        ans = solution.longestWPI(new int[]{6,9,6,9,9});
        System.out.println(ans);
        ans = solution.longestWPI(new int[]{6,9,6,9});
        System.out.println(ans);
    }
    
}
