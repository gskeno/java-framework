package com.gson.algo.greedy;

/**
 * 股票售卖最佳时机
 * https://www.nowcoder.com/practice/64b4262d4e6d4f6181cd45446a5821ec
 *
 * 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
 * 2.如果不能获取到任何利润，请返回0
 * 3.假设买入卖出均无手续费
 *
 *
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * [8,9,2,5,4,7,1]
 */
public class MaxProfit {

    public int maxProfit1(int[] nums){
        if (nums.length <=1){
            return 0;
        }
        // 1. 遍历一次nums数组
        // 2. minPrice 记录遍历数组到当前位置时的最低股票价格
        // 3. maxProfit记录遍历数组到当前位置时的最大收益
        // 4. maxProfitIfSoldNow 记录如果当前位置卖出时的最大收益
        int minPrice = nums[0];
        int maxProfit = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] - minPrice > maxProfit)){
                maxProfit = nums[i] - minPrice;
            }
            minPrice = Math.min(minPrice, nums[i]);

        }
        return maxProfit;
    }
}
