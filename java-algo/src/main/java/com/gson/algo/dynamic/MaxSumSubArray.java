package com.gson.algo.dynamic;

/**
 * <p>https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484?tpId=13&tqId=11183&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey</p>
 *
 * 输入一个整型数组，数组里有正数也有负数。
 * 数组中的一个或连续多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为 O(n).
 */
public class MaxSumSubArray {
    /**
     * 设置dp[i]表示第i个元素为末尾元素的子数组的和的最大值,i的起始值为1
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0){
            return 0;
        }

        int ret = array[0];
        int[] dp = new int[array.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= array.length; i++) {
            //dp[i]表示第i个元素为末尾元素的子数组的最大和
            //要么为第i-1个元素为末尾元素的子数组的最大和 + 第i个元素的值
            //要么为第i个元素的值（当第i-1个元素为末尾元素的子数组的最大和为负数）

            dp[i] = Math.max(dp[i-1] + array[i-1], array[i-1]);
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    /**
     * 设置dp[i]表示第i个元素为末尾元素的子数组的和的最大值,i的起始值为1
     * 如果dp[i-1] 小于0，则dp[i] = array[i-1]
     * 如果dp[i-1] 大于0, 则dp[i] = array[i-1] + dp[i-1]
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray2(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            array[i] += array[i-1] >0? array[i-1] : 0;
            max = Math.max(max, array[i]);
        }
        return max;
    }
}
