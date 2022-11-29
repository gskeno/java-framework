package com.gson.algo.search;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
 *
 * 数组中可能存在重复元素
 */
public class 搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return true;
            }
            if (nums[mid] < nums[right]){
                if (nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }else if (nums[mid] > nums[right]){
                if (nums[mid] > target && nums[left] <= target){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                right --;
            }
        }
        return nums[left] == target ? true : false;
    }

    public static void main(String[] args) {
        搜索旋转排序数组II solution = new 搜索旋转排序数组II();
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},0 ));
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},1 ));
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},2 ));
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},3 ));
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},4 ));
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},5 ));
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2},6 ));
    }
}
