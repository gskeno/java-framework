package com.gson.algo.leetcode;

import com.sun.tools.javac.code.Attribute;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class 买卖股票的最佳时机II {

    public int maxProfit(int[] prices) {
        Integer lower = null;
        Integer higher = null;
        int ans = 0;

        for (int i = 0; i < prices.length; i++) {
            // 寻找当前段的股票最低点
            if (lower == null || prices[i] < lower){
                lower = prices[i];
                continue;
            }
            // 寻找当前段的股票最高点
            if (higher == null || prices[i] >= higher){
                higher = prices[i];
            }

            // 判断股票买卖区间是否已经出现
            if (i == prices.length - 1 || prices[i+1] < higher){
                // 走到这里，说明一个股票买卖区间出现了
                ans+= higher - lower;
                lower = prices[i];
                higher = null;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        买卖股票的最佳时机II solution = new 买卖股票的最佳时机II();
        int ans = 0;

        ans = solution.maxProfit(new int[]{2,8,1,7,3,5,4});
        System.out.println(ans);

        ans = solution.maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(ans);

        ans = solution.maxProfit(new int[]{1,2,3,4,5});
        System.out.println(ans);

        ans = solution.maxProfit(new int[]{7,6,4,3,1});
        System.out.println(ans);



    }
}
