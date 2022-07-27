package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 * 一次爬1层或者2层，爬到n层有多少种方法
 */
public class 爬楼梯_70 {
    /**
     * 设f(n)表示爬n层楼梯的方法数
     * 
     * 方式1: 最后一次爬两层，方法数有 f(n-2)
     * 方式2: 最后一次爬一层，方法数有 f(n-1)
     * 
     * 即f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n+1];

        for (int i = 1; i <=n ; i++) {
            // 爬1层只有1种方法
            if (i == 1){
                dp[i] = 1;
            // 爬2层有2种方法
            }else if (i == 2){
                dp[i] = 2;
            }else {
                dp[i] = dp[i-2] + dp[i-1];
            }
        }
        return dp[n];
    }
}
