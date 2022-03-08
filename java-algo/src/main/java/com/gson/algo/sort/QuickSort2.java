package com.gson.algo.sort;

import java.util.Arrays;

public class QuickSort2 {
    /**
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
