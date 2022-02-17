package com.gson.algo.dynamic.plan;

/**
 * 粉刷房子
 *
 * 一排n栋房子要粉刷成红色、绿色和蓝色，不同房子被粉刷成不同颜色的成本不同。
 * 用一个 n x 3 的数组表示n栋房子分别用3种颜色粉刷的成本。
 * 要求任意相邻的两栋房子的颜色都不一样，
 * 请计算粉刷这n栋房子的最少成本。
 * 例如，粉刷3栋房子的成本分别为
 * [
 *  [17,2,16],   第一栋房子粉刷成红、绿、蓝的成本分别为 17, 2, 16
 *  [15,14,5],   第二栋...
 *  [13,3,1]     第三栋...
 * ]
 * 如果分别将这3栋房子粉刷成绿色、蓝色、绿色，那么粉刷的成本就是 2 + 5 + 3 = 10, 是最少的成本
 */
public class PaintHouse {
    // 定义r(i) 表示第i栋粉刷成红色，粉刷了0至i栋后的最少成本
    // 定义g(i) 表示第i栋粉刷成绿色，粉刷了0至i栋后的最少成本
    // 定义b(i) 表示第i栋粉刷成蓝色，粉刷了0至i栋后的最少成本
    // 定义cost[m][n] 表示 第m栋房子粉刷成红色的成本(n=0)，或者粉刷成绿色的成本(n=1)，或者粉刷成蓝色的成本(n=2)
    // 则存在关系 r(i) = Math.min( g(i-1), b(i-1) ) + cost[i][0]
    //          g(i) = Math.min( r(i-1), b(i-1) ) + cost[i][1]
    //          b(i) = Math.min( r(i-1), g(i-1) ) + cost[i][2]
    //          r(0) = cost[0][0], g(0) = cost[0][1], b(0) = cost[0][2]

    public int minCost(int[][] costs){
        if (costs.length == 0){
            return 0;
        }
        // 三行，表示红，绿，蓝
        // 两列，表示最近的两个房子刷颜色后的最小成本
        int[][] dp =new int[3][2];
        for (int j =0; j < 3; j++){
            // 即 dp[0][0] = cost[0][0] 第0栋房子刷红色的最小成本
            // 即 dp[1][0] = cost[0][1] 第0栋房子刷绿色的最小成本
            // 即 dp[2][0] = cost[0][2] 第0栋房子刷蓝色的最小成本
            dp[j][0] = costs[0][j];
        }

        // costs.length 表示房子的栋数
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < 3; j++) {
                // 处理第i 栋房子刷某种颜色A时，先求出第i-1栋房子刷其他颜色时的最小成本
                // 再加上第i栋房子使用颜色A时的成本
                int prev1 = dp[(j+2)%3][(i-1)%2];
                int prev2= dp[(j+1)%3][(i-1)%2];
                dp[j][i%2] = Math.min(prev1, prev2) + costs[i][j];
            }
        }

        int last = (costs.length - 1)%2;
        // 最后一栋房子刷红色，绿色，蓝色的成本取最小值
        return Math.min(dp[0][last], Math.min(dp[1][last], dp[2][last]));
    }

    public static void main(String[] args) {
        int[][] costs = {{17,2,16}, {15,14,5}, {13,3,1}};
        PaintHouse paintHouse = new PaintHouse();
        int minCost = paintHouse.minCost(costs);
        System.out.println(minCost);
    }
}
