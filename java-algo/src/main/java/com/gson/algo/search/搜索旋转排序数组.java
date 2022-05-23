package com.gson.algo.search;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 数组中的值各不相同
 */
public class 搜索旋转排序数组 {
    /**
     * 如 [7,8,2,3,4]
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right){
            int mid = left + (right-left)/2;
            if (nums[mid] == target){
                return mid;
            }
            // mid左侧是递增数组,第一次时这里少了个等于号，{3,1},1的case通不过
            if (nums[mid] >= nums[left]){
                // 目标在有序数组中
                if (nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
            // mid右侧是递增数组
            else {
                // 目标在有序数组中
                if (target >nums[mid] && target <= nums[right]){
                    left = mid +1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        搜索旋转排序数组 solution = new 搜索旋转排序数组();
        System.out.println(solution.search(new int[]{4,5,6,7,0,1,2},0 ));
    }
}
