package com.gson.algo.sort;

import java.util.Arrays;

public class QuickSort2 {
    /**
     * 从A[mid] = a左边找一个比a大的，从A[mid]右边找一个比a小的，两者交换位置。继续再找
     *
     * 当两者的寻找指针交错后，停止，构建两个局部数组，再重复此过程
     * 快速排序， 递归  分治思想  由上到下
     * @param A
     * @param start
     * @param end
     */
    public  void quickSort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        int pivot = A[(start + end) / 2];

        // key point 2: every time you compare left & right, it should be
        // left <= right not left < right
        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(A, left, right);
                left++;
                right--;
            }
        }
        quickSort(A, start, right);
        quickSort(A, left, end);
    }

    private void swap(int[] nums, int index1, int index2){
        if (index1 != index2){
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,12,8,  7,  9,10,13,11};
        QuickSort2 quickSort2 = new QuickSort2();
        quickSort2.quickSort(nums, 0, nums.length-1);

        System.out.println(Arrays.toString(nums));
    }
}
