package com.gson.algo.search;

public class 寻找旋转排序数组中的最小值II {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left)/2;
            if (nums[mid] < nums[right]){
                // right肯定变小了
                right = mid;
            }else if (nums[mid] > nums[right]){
                // left肯定变大了
                left = mid + 1;
            }else {
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        寻找旋转排序数组中的最小值II solution = new 寻找旋转排序数组中的最小值II();
        int ans = solution.findMin(new int[]{10, 1, 10, 10, 10});
        System.out.println(ans);
    }
}
