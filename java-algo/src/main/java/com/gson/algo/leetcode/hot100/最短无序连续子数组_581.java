package com.gson.algo.leetcode.hot100;

public class 最短无序连续子数组_581 {

    /**
     * [2,6,4,8,10,9,15]
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;

        // 倒着遍历，从pivot2开始出现不递减
        int pivot2 = -1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                pivot2 = i;
                break;
            }
        }

        // 说明倒序一直是递减的，则正序一直是递增的
        if (pivot2 == -1) {
            return 0;
        }

        // 从pivot2开始往前寻找最大的元素
        int max = nums[pivot2];
        for (int i = pivot2 - 1; i >= 0; i--) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        int end = nums.length;

        // 从尾部找最后一个>=max的元素
        for (int i = len - 1; i > pivot2; i--) {
            if (nums[i] >= max) {
                end = i;
            }
        }


        // 从pivot1为止开始出现不递增
        int pivot1 = -1;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]) {
                pivot1 = i;
                break;
            }
        }


        // 从pivot1开始往后寻找最小的元素及其位置
        int min = nums[pivot1];
        for (int i = pivot1 + 1; i < len; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        int begin = 0;
        // 再从头部找第一个大于 min的元素
        for (int i = 0; i < pivot1; i++) {
            if (nums[i] > min) {
                begin = i;
                break;
            }
        }

        return end - begin;

    }

    public static void main(String[] args) {
        最短无序连续子数组_581 solution = new 最短无序连续子数组_581();
//        System.out.println(solution.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{1,2,3,4}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{1}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{2,1}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{1,3,2,4,5}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{1, 3, 2, 3, 3}));
        System.out.println(solution.findUnsortedSubarray(new int[]{2, 3, 3, 2, 4}));
    }
}
