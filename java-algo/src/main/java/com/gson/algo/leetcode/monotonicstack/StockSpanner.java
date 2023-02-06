package com.gson.algo.leetcode.monotonicstack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/online-stock-span/
 *
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 *
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 *
 * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
 *
 * 实现 StockSpanner 类：
 *
 * StockSpanner() 初始化类对象。
 * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 *
 */
public class StockSpanner {
    private Stack<int[]> stack = new Stack<>();
    private int idx = -1;
    public StockSpanner() {
        stack.push(new int[]{ idx, Integer.MAX_VALUE});
    }

    /**
     * 如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
     * @param price
     * @return
     */
    public int next(int price) {
        idx++;
        // 维护单调递减栈
        while (price >= stack.peek()[1]){
            stack.pop();
        }
        // stack.peek()是上一个大于price的价格
        int ans = idx - stack.peek()[0];
        stack.push(new int[]{idx, price});
        return ans;

    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }
}
