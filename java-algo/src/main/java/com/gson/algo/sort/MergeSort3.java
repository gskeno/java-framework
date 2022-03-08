package com.gson.algo.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort3 {
    public int[] sortArray(int[] nums){
        int[] dst = Arrays.copyOf(nums, nums.length);
        mergeSort(nums, dst, 0, nums.length);
        return dst;
    }

    /**
     *
     * @param src
     * @param dst
     * @param start
     * @param end 元素下标不含end该边界
     */
    private void mergeSort(int[] src, int[] dst, int start, int end){
        if (start + 1 >= end){
            return;
        }
        int mid = (start+end)/2;
        mergeSort(dst, src, start, mid);
        mergeSort(dst, src, mid, end);


        int i =start, j = mid, k = start;

        while (i < mid | j <end){
            if (j == end || (i < mid && src[i] < src[j])){
                dst[k++] = src[i++];
            }else {
                dst[k++] = src[j++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort3 mergeSort3 = new MergeSort3();
        int[] nums = {4,1,5,6,2,7,8,3};
        int[] result = mergeSort3.sortArray(nums);
        System.out.println(Arrays.toString(result));
    }
}
