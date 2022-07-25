package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * <p>
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class 分割等和子集_416 {


    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum >> 1;

        // 我们需要从nums数组中挑选若干个数，使其和为target。
        // 设boolean dp[i][j]表示从nums[0]到nums[i]构成的子数组是否可以找到若干元素，使其和为j。
        // 则dp[n-1][target]就是答案,我们可以选择 挑选nums[n-1]使其和为target,
        // 也可以选择 不挑选nums[n-1]使其和为target。因此
        // 存在以下关系
        // dp[i][j] = dp[i-1][j-nums[i]]   |  dp[i-1][j]

        boolean[][] dp = new boolean[len][target + 1];

        // 初始化dp[0]
        for (int j = 0; j <= target; j++) {
            if (nums[0] == j) {
                dp[0][j] = true;
            }
        }

        // 第0列否为false，因为任何子数组之和都不能为0


        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0 && !dp[i][j]) {
                    dp[i][j] = dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[len-1][target];


       // return dfs(nums, 0, target);

    }

    /**
     * 递归超出时间限制
     * <p>
     * 从nums的第idx个元素开始做决策，可以不选idx元素，也可以选择idx元素，任意一种选择方式
     * 能使最终和为target，则返回true
     *
     * @param nums
     * @param idx
     * @param target
     * @return
     */
    public boolean dfs(int[] nums, int idx, int target) {
        if (target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        if (idx == nums.length) {
            return target == 0;
        }

        if (dfs(nums, idx + 1, target)) {
            return true;
        }

        if (dfs(nums, idx + 1, target - nums[idx])) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        分割等和子集_416 solution = new 分割等和子集_416();
        System.out.println(solution.canPartition(new int[]{1,5,11,5}));
        System.out.println(solution.canPartition(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97}));
    }
}
