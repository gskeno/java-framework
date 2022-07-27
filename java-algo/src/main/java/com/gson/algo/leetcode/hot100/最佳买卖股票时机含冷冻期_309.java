package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * <p>
 * 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class 最佳买卖股票时机含冷冻期_309 {

    /**
     * 使用动态规划,我们简单的认为交易都是发生在一天的结束时刻
     *
     * dp[i][0]表示第i天结束时，仍然持有股票时的累计最大利润
     * dp[i][1]表示第i天结束时不持有股票，但属于冷冻期(即下一天不可交易)时的累计最大利润
     * dp[i][2]表示第i天结束时不持有股票，且不属于冷冻期(即下一天可交易)时的累计最大利润
     *
     * a:
     * 第i天结束时持有股票，可以是第i-1天结束时也持有股票，
     * 或者第i-1天结束时不持有股票，且不属于冷冻期，第i天结束时买股票
     * 则有dp[i][0] = max(dp[i-1][0], dp[i-1][2]-price[i])
     *
     * b:
     * 第i天结束时不持有股票，且属于冷冻期，第i天结束时卖出股票
     * dp[i][1]= dp[i-1][0] + price[i]
     *
     * c:
     * 第i天结束时，不持有股票，且不属于冷冻期，有两种情况
     * 1. 第i天无任何操作，i-1天结束时卖出股票
     * 2. 第i天无任何操作，i-1天也无任何操作
     *
     * dp[i][2]=max(dp[i-1][1], dp[i-1][2])
     *
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices){
        int n = prices.length;
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0]+ prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }

        return Math.max(Math.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
    }

    /**
     * 可惜没有考虑冷冻期
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        int lowIdx = 0, highIdx = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < prices[i - 1]) {
                ans += prices[highIdx] - prices[lowIdx];
                lowIdx = i;
                continue;
            } else {
                highIdx = i;
            }

            if (highIdx == n - 1) {
                ans += prices[highIdx - lowIdx];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        最佳买卖股票时机含冷冻期_309 solution = new 最佳买卖股票时机含冷冻期_309();
        System.out.println(solution.maxProfit(new int[]{1,2,3,0,2}));
    }
}
