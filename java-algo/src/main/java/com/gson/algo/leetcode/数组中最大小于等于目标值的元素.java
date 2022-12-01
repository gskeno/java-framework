package com.gson.algo.leetcode;

public class 数组中最大小于等于目标值的元素 {

    public int search(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        // 最经典的二分法
        while (left < right){
            int mid = (left + right)/2;
            if (nums[mid] >= target){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums[left] <= target ? nums[left] : (left > 0 ? nums[left-1] : - 1);
    }

    public static void main(String[] args) {
        数组中最大小于等于目标值的元素 solution = new 数组中最大小于等于目标值的元素();
        System.out.println(solution.search(new int[]{1,3,5,7,8}, 6));
    }
}
