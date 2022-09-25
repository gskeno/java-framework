package com.gsonkeno.dsaa.algorithms;

import java.util.Arrays;

/**
 * Created by gaosong on 2017-12-28
 * 逆序对
 *
 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
 * 概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。
 *
 * 序列 [2, 4, 1, 3, 5] 中，有 3 个逆序对 (2, 1), (4, 1), (4, 3)，则返回 3 。
 */
public class ReversePairs {
    /**
     * @param A an array
     * @return total of reverse pairs
     */
    public static  long reversePairs(int[] A) {
        return mergeSort(A, 0, A.length - 1);
    }

    private static  int mergeSort(int[] A, int start, int end) {
        System.out.println("mergeSort:" + Arrays.toString(A) + "-" + start + "-" + end);
        if (start >= end) {
            return 0;
        }

        int mid = (start + end) / 2;
        int sum = 0;
        sum += mergeSort(A, start, mid);
        sum += mergeSort(A, mid+1, end);
        sum += merge(A, start, mid, end);
        return sum;
    }

    private static  int merge(int[] A, int start, int mid, int end) {

        System.out.println("merge:" + Arrays.toString(A) + "-" + start + "-" + mid + "-" + end);
        int[] temp = new int[A.length];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int index = start;
        int sum = 0;

        while (leftIndex <= mid && rightIndex <= end) {
            if (A[leftIndex] <= A[rightIndex]) {
                temp[index++] = A[leftIndex++];
            } else {
                temp[index++] = A[rightIndex++];
                sum += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid) {
            temp[index++] = A[leftIndex++];
        }
        while (rightIndex <= end) {
            temp[index++] = A[rightIndex++];
        }

        for (int i = start; i <= end; i++) {
            A[i] = temp[i];
        }

        return sum;
    }
}
