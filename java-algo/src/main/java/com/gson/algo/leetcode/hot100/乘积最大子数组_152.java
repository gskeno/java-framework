package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/maximum-product-subarray/
 * 给你一个整数数组 nums，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 测试用例的答案是一个32-位 整数。
 * <p>
 * 子数组 是数组的连续子序列。
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证是一个 32-位 整数
 */
public class 乘积最大子数组_152 {

    public int maxProduct(int[] nums) {
        // 遍历到i时，以i位置元素结尾的子数组乘积的最大值和最小值
        int minI = nums[0];
        int maxI = nums[0];
        int max = maxI;


        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                minI = maxI = 0;
            } else {
                int tmp = minI;
                minI = Math.min(Math.min(minI * nums[i], maxI * nums[i]), nums[i]);
                maxI = Math.max(Math.max(tmp * nums[i], maxI * nums[i]), nums[i]);
            }
            max = Math.max(max, maxI);
        }
        return max;
    }

    public static void main(String[] args) {
        乘积最大子数组_152 solution = new 乘积最大子数组_152();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{-2, 0, -1}));
    }
}
