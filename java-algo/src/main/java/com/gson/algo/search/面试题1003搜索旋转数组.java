package com.gson.algo.search;

/**
 * https://leetcode.cn/problems/search-rotate-array-lcci/
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 * 示例2:
 *
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 * 提示:
 *
 * arr 长度范围在[1, 1000000]之间
 *
 */
public class 面试题1003搜索旋转数组 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int minIdx = Integer.MAX_VALUE;
        while (left < right){
            int mid = left + (right - left)/2;
            // 情况1，左右边界值相等
            if (nums[right] == nums[left]){
                right--;
                continue;
            }
            // 情况2，中间值等于目标值
            if (nums[mid] == target){
                minIdx = mid;
                right--;
                continue;
            }
            // 情况3，后半段递增
            if (nums[mid] < nums[right]){
                // 情况3.a, 目标值在后半段中
                if (nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }
                // 情况3.b, 目标值不在后半段中
                else {
                    right = mid - 1;
                }
            }
            // 情况4，前半段递增
            else if (nums[mid] > nums[right]){
                // 情况4.a, 目标值在前半段中
                if (nums[left] <= target && target <  nums[mid]){
                    right = mid - 1;
                }
                // 情况4.b,目标值在后半段中
                else {
                    left = mid + 1;
                }
            }
            // nums[mid] == nums[right],但可能并不是一条直线。
            else {
                right --;
            }
        }
        return minIdx == Integer.MAX_VALUE ? (nums[left] == target ? left : -1) : minIdx;
    }

    public static void main(String[] args) {
        面试题1003搜索旋转数组 solution = new 面试题1003搜索旋转数组();
        int ans ;
//        ans = solution.search(new int[]{1,1,1,1,1,2,1,1,1}, 2);
        ans = solution.search(new int[]{5,5,5,1,2,3,4,5}, 5);
        System.out.println(ans);
//        ans = solution.search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 5);
//        System.out.println(ans);
//        ans = solution.search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 11);
//        System.out.println(ans);
    }
}
