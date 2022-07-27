package com.gson.algo.leetcode.hot100;

import java.util.Arrays;

public class 在排序数组中查找元素的第一个和最后一个位置_34 {


    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }

        int endIdx = index + 1;
        for (; endIdx < nums.length; endIdx++) {
            if (nums[endIdx] != target) {
                break;
            }
        }

        return new int[]{index, endIdx - 1};
    }

    /**
     * 二分查找nums数值中第一个为target的元素所在位置，
     * 如果不存在target元素，返回-1
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                ans = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        在排序数组中查找元素的第一个和最后一个位置_34 solution = new 在排序数组中查找元素的第一个和最后一个位置_34();
        int[] ans;
        ans = solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(ans));
    }
}
