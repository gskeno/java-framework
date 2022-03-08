package com.gson.algo.sort;

/**
 * 归并排序(非递归)
 */
public class MergeSort2 {

    public int[] sortArray(int[] nums){
        int length = nums.length;
        int[] src = nums;
        int[] dest = new int[length];

        // 段大小1变为2变为4，
        for (int seg = 1; seg < length; seg = seg*2) {

        }
    }
}
