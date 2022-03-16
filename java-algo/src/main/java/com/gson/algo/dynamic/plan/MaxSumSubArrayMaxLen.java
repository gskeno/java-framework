package com.gson.algo.dynamic.plan;


import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/11662ff51a714bbd8de809a89c481e21
 * 连续子数组最大和，求对应的子数组，如果子数组存在多个，返回长度最长的那一个
 *
 * 输入：
 * [1,-2,3,10,-4,7,2,-5]
 * 返回值：
 * [3,10,-4,7,2]
 */
public class MaxSumSubArrayMaxLen {
    /**
     * array的长度至少为1
     * @param array
     * @return
     */
    public int[] maxSumSubArrayMaxLen(int[] array) {
        // 快照，记录遍历过程中满足要求的最长子数组的最大和
        int maxSum = array[0];
        // 快照，记录遍历过程中满足要求的最长子数组长度
        int maxLength = 1;
        // 快照，记录遍历过程中满足要求的最长子数组的起始索引，inclusive
        int leftSnapshot = 0;
        // 快照，记录遍历过程中满足要求的最长子数组的结束索引，inclusive
        int rightSnapshot = 0;
        // 指针，记录遍历过程中可能会满足要求的最长子数组的起始索引，inclusive
        int left = 0;
        // 指针，记录遍历过程中可能会满足要求的最长子数组的结束索引，inclusive
        int right = 0;
        // dp[i]表示遍历过程中到array[i]为止的符合要求的连续子数组最大和
        // 存在 dp[i] = max(dp[i-1]+array[i], array[i])
        // 说明，如果dp[i-1]<0,表示array[i]之前连续子数组最大和小于0，那么到array[i]为止的连续子数组最大和就是只选择array[i]即可
        // 如果dp[i-1]>=0,表示array[i]之前连续子数组最大和不小于0，那么到array[i]为止的连续子数组最大和就是dp[i-1]+array[i]
        int[] dp = new int[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            right++;
            dp[i] = Math.max(dp[i-1] + array[i], array[i]);
            // 需要从当前元素从新开始探索，寻求找到一个最大和连续子数组
            if (dp[i-1] < 0){
                left = right;
            }
            if (dp[i] > maxSum || (dp[i] == maxSum && (right-left+1) > maxLength)){
                maxLength = right-left+1;
                maxSum = dp[i];
                leftSnapshot = left;
                rightSnapshot = right;
            }
        }

        int[] res = new int[maxLength];
        int index = 0;
        for (int i = leftSnapshot; i <= rightSnapshot; i++) {
            res[index++] = array[i];
        }
        return res;
    }

    public static void main(String[] args) {
        // int[] ori = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        int[] ori = new int[]{-1,1,2,1};
        int[] ints = new MaxSumSubArrayMaxLen().maxSumSubArrayMaxLen(ori);
        System.out.println(Arrays.toString(ints));
    }
}
