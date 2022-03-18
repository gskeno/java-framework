package com.gson.algo.dynamic;

/**
 * 一个数组cost的所有数字都是正数，
 * 它的第i个数字表示在一个楼梯的第i级台阶往上爬的成本，
 * 在支付了成本cost[i]之后可以从第i级台阶往上爬1级或2级。
 * 假设台阶至少有2级，既可以从第0级台阶出发，也可以从第1级台阶出发，
 * 请计算爬上该楼梯的最小成本
 *
 */
public class MinCostClimbingStairs {

    /**
     * 重点是找出状态转移方程
     * @param cost
     * @return
     */
    int minCostClimbingStairs(int[] cost){
        // 用函数 f(i) 表示从楼梯的第 i 级台阶再往上爬这种方案的最少成本
        // f(i) = min(f(i-1), f(i-2)) + cost[i]
        int len = cost.length;
        return Math.min(help(cost, len-1), help(cost, len-2));
    }

    private int help(int[] cost, int i){
        if (i<2){
            return cost[i];
        }
        return Math.min(help(cost, i-2), help(cost, i-1)) + cost[i];
    }

    public static void main(String[] args) {
        int[] cost = {1,100,1,1,100,1};
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int result = minCostClimbingStairs.minCostClimbingStairs(cost);
        System.out.println(result);
    }
}
