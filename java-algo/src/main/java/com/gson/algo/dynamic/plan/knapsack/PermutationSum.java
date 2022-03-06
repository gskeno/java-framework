package com.gson.algo.dynamic.plan.knapsack;

/**
 * 给定一个非空的正整数数组nums和一个目标值t，
 * 数组中的所有数字都是唯一的，请计算数字之和等于t的
 * 所有排列的数目。
 *
 * 如[1,2,3], 目标值t为3，那么总共又4个组合的数字之和等于3，他们分别为
 * {1,1,1}, {1,2,}, {2,1}, 及{3}
 */
public class PermutationSum {

    /**
     * 分析状态转移方程是关键
     * 用f(i)表示和为i的排列的数目。为了得到和为i的排列，有如下选择:
     *
     * 在和为i-nums[0]的排列中末尾添加标号为0的数字，此时f(i) = f(i-nums[0])
     *
     * 在和为i-nums[1]的排列中末尾添加标号为1的数字，此时f(i) = f(i-nums[1])
     *
     * 在和为i-nums[n-1]的排列中添加标号为n-1的数字(n为数组的长度), 此时f(i)=f(i-nums[n-1])
     *
     * 目标是求出所有和为i的排列的数目，所以将上述所有情况全部累加起来。该状态转移方程可以表示为
     * f(i) = 求和 f(i-nums[j]) 其中 nums[j] <=i
     *
     * 且有f(0) = 1
     */

    public int premutationSum(int[] nums, int target){
        // dp存储f(i)结果
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; ++i) {
            for(int num : nums){
                if ( i >= num){
                    int old = dp[i];
                    dp[i] += dp[i - num];
                    System.out.println("和为i=" + i + "的排列数目为dp[" + i + "]="
                            + dp[i] + ",其为old=" + old + "再加上dp[" + (i-num) + "]=" + dp[i-num] + ",此时num=" + num);
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        PermutationSum permutationSum = new PermutationSum();
        int[] nums = {1,2,3};
        int target = 3;
        int count = permutationSum.premutationSum(nums, target);
        System.out.println(count);
    }
}
