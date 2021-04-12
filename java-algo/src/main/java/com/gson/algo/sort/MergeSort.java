package com.gson.algo.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    /**
     * 假设a,b ,返回a,b合并后的排序结果
     *
     * @param a
     * @param b
     * @return
     */
    public static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int ai = 0;
        int bi = 0;
        int index = 0;
        while (true) {
            if (index == c.length) {
                break;
            }
            if (ai == a.length && bi < b.length) {
                c[index] = b[bi];
                index++;
                bi++;
            } else if (bi == b.length && ai < a.length) {
                c[index] = a[ai];
                index++;
                ai++;
            } else if (ai < a.length && bi < b.length && a[ai] < b[bi]) {
                c[index] = a[ai];
                index++;
                ai++;
            } else if (bi < b.length && ai < a.length && a[ai] >= b[bi]) {
                c[index] = b[bi];
                index++;
                bi++;
            }
        }
        return c;
    }


    public static int[] sort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int half = array.length / 2;
        int[] a = Arrays.copyOfRange(array, 0, half);
        int[] b = Arrays.copyOfRange(array, half, array.length);
        int[] sortA = sort(a);
        int[] sortB = sort(b);
        return merge(sortA, sortB);
    }

    public static void main(String[] args) {
        int[] c = {7, 1, 8, 6};
        int[] a = {1, 7, 8};
        int[] b = {2, 6, 10};
        //int[] merge = merge(a, b);
        int[] sort = sort(c);
        System.out.println(Arrays.toString(sort));
    }
}
