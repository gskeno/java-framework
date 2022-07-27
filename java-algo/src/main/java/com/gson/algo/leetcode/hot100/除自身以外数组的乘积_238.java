package com.gson.algo.leetcode.hot100;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/
 * <p>
 * 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
 * <p>
 * 题目数据 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * <p>
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 */
public class 除自身以外数组的乘积_238 {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];
        int[] ans = new int[n];

        // L[i]表示i位置左侧所有元素的乘积
        // R[i]表示i位置右侧所有元素的乘积
        // 则ans[i] = L[i] * R[i]
        L[0] = 1;
        R[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        除自身以外数组的乘积_238 solution = new 除自身以外数组的乘积_238();
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{1,2,3,4})));
    }
}
