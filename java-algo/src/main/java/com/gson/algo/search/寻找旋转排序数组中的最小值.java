package com.gson.algo.search;

public class 寻找旋转排序数组中的最小值 {

    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int min = Integer.MAX_VALUE;
        while(left <= right){
            int mid = left + (right - left)/2;
            // 说明只剩下1或者2个元素
            if(mid == left){
                return Math.min(Math.min(nums[left], nums[right]), min);
            }
            // 说明左半部分是递增的，
            // 右半部分有 先递增，后递减,且最后一个元素小于左半部分第一个元素
            if(nums[mid] > nums[left]){
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }else{
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        寻找旋转排序数组中的最小值 solution = new 寻找旋转排序数组中的最小值();
        // int min = solution.findMin(new int[]{4, 5,6, 7, 0, 1, 2});
        int min = solution.findMin(new int[]{6,7,8,1,2,3,4,5});
        System.out.println(min);
    }
}
