package com.gson.algo.sort;

import com.gson.algo.search.Bisection;

/**
 * 二分法
 */
public class BisectionSort {

    /**
     * 获取nums中最近的且比target小的元素的位置
     * 如 1 7 7 7 8, 7, 返回0
     * 如 1 7 7 7 8, 8, 返回3
     *
     * @param nums
     * @param target
     * @return
     */
    public static int getNearestLessIndex(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        int pos = 0;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] < target){
                pos = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 7, 7, 8};
        int target = 7;
        int nearestLessIndex = BisectionSort.getNearestLessIndex(nums, target);
        System.out.println(nearestLessIndex);

        nums = new int[]{1, 7, 7, 7, 8};
        target = 8;
        nearestLessIndex = BisectionSort.getNearestLessIndex(nums, target);
        System.out.println(nearestLessIndex);

        nums = new int[]{1, 7, 7, 7, 8};
        target = 0;
        nearestLessIndex = BisectionSort.getNearestLessIndex(nums, target);
        System.out.println(nearestLessIndex);
    }
}
