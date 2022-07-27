package com.gson.algo.leetcode.hot100;

/**
 *
 */
public class 目标和_494 {

    /**
     * 回溯法
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        return findTargetSumWays(nums, len - 1, target);
    }

    /**
     * 从nums[0]到nums[endIdx]和为target的表达式总数
     * @param nums
     * @param endIdx
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int endIdx, int target) {
        // 递归结束
        if (endIdx == 0){
            int ways = 0;
            if (nums[endIdx] == target){
                ways++;
            }

            if (-nums[endIdx] == target){
                ways++;
            }
            return ways;
        }

        // 最后一位采用+符号的表达式总数
        int res1 = findTargetSumWays(nums, endIdx-1, target - nums[endIdx]);

        // 最后一位采用-符号的表达式总数
        int res2 = findTargetSumWays(nums, endIdx - 1, target + nums[endIdx]);

        return res1 + res2;
    }

    public static void main(String[] args) {
        目标和_494 solution = new 目标和_494();
        System.out.println(solution.findTargetSumWays(new int[]{1,1,1,1,1}, 3));
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1));
    }


}
