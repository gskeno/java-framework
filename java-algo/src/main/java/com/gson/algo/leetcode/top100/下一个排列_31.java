package com.gson.algo.leetcode.top100;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/
 */
public class 下一个排列_31 {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return;
        }

        if (n == 2) {
            swap(nums, 0, 1);
            return;
        }

        int pivot = -1;
        // 从后往前遍历，到nums[pivot]时才产生降序，在这之前一直是升序
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] - nums[i + 1] < 0) {
                pivot = i;
                break;
            }
        }

        // 数组一直是递减的，翻转即可
        if (pivot == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        reverse(nums, pivot + 1, n - 1);

        // 从pivot+1开始寻找第一个比nums[pivot]大的数,进行翻转结束
        for (int i = pivot + 1; i < n; i++) {
            if (nums[i] > nums[pivot]) {
                swap(nums, pivot, i);
                return;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 对nums从i到j进行翻转
     */
    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        下一个排列_31 solution = new 下一个排列_31();
        int[] params = new int[]{1, 2, 3};
        solution.nextPermutation(params);
        System.out.println(Arrays.toString(params));

        params = new int[]{3, 2, 1};
        solution.nextPermutation(params);
        System.out.println(Arrays.toString(params));

        params = new int[]{1, 1, 5};
        solution.nextPermutation(params);
        System.out.println(Arrays.toString(params));
    }
}
