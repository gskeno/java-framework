package com.gson.algo.leetcode;

public class 找到最左一个大于等于目标值的二分法 {

    /**
     *
     * 找到最左一个大于等于目标值的二分法
     * @param nums 非严格递增
     * @param target 目标值
     * @return
     */
    public int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        // 记录最左 >= 目标值 的下标
        int index = -1;
        // 这里一定要使用<=, 否则nums=[1,7,8], target=8时，函数返回-1,应该返回2
        while (left <= right){
            int mid = left + (right - left)/2;
            // left和right 之一会变化，保证了可以跳出while循环
            if (nums[mid] >= target){
                index = mid;
                right = mid -1;
            }else {
                left = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        找到最左一个大于等于目标值的二分法 solution = new 找到最左一个大于等于目标值的二分法();
        System.out.println(solution.binarySearch(new int[]{1,7,7,7,8,8,8,8,9}, 8));
        System.out.println(solution.binarySearch(new int[]{1,7,7,7,8,8,8,8,9}, 9));
        System.out.println(solution.binarySearch(new int[]{1,7,7,7,8,8,8,8,9}, 10));
        System.out.println(solution.binarySearch(new int[]{1,7,7,7,8,8,8,8,9}, 0));

        System.out.println(solution.binarySearch(new int[]{1,7,7,7,8}, 7));
        System.out.println(solution.binarySearch(new int[]{1,7,7,8}, 7));
        System.out.println(solution.binarySearch(new int[]{1,7,8}, 7));
        System.out.println(solution.binarySearch(new int[]{1,7,8}, 1));
        System.out.println(solution.binarySearch(new int[]{1,7,8}, 8));

    }
}
