package com.gsonkeno.dsaa.sort;

import java.util.Arrays;

/**
 * 归并排序<br>
 * 参考文档 <a href="https://www.cnblogs.com/jingmoxukong/p/4308823.html">博客 </a>
 * Created by gaosong on 2017-12-06
 * 其实命名为MergeSort也是不错的选择
 * 时间复杂度为O(nlogn), 空间复杂度为O(n)
 */
public class DivideAndConquerSort {

    /**
     * 将两个有序序列合并为一个有序序列
     * @param array
     * @param low 第一个有序序列的开始位置
     * @param mid 第一个有序序列的结束位置
     * @param high 第二个有序序列的结束位置
     */
    public void merge(int[] array, int low, int mid, int high) {
        int i = low; // i是第一段序列的下标
        int j = mid + 1; // j是第二段序列的下标
        int k = 0; // k是临时存放合并序列的下标
        int[] array2 = new int[high - low + 1]; // array2是临时合并序列

        // 扫描第一段和第二段序列，直到有一个扫描结束
        while (i <= mid && j <= high) {
            // 判断第一段和第二段取出的数哪个更小，将其存入合并序列，并继续向下扫描
            if (array[i] <= array[j]) {
                array2[k] = array[i];
                i++;
                k++;
            } else {
                array2[k] = array[j];
                j++;
                k++;
            }
        }

        // 若第一段序列还没扫描完，将其全部复制到合并序列
        while (i <= mid) {
            array2[k] = array[i];
            i++;
            k++;
        }

        // 若第二段序列还没扫描完，将其全部复制到合并序列
        while (j <= high) {
            array2[k] = array[j];
            j++;
            k++;
        }

        // 将合并序列复制到原始序列中
        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = array2[k];
        }
    }

    /**
     *
     * @param array 待排序的数组
     * @param gap   一趟归并排序前有序自表的长度。特别的，第一趟归并排序时，gap=1
     * @param length 待排序数组的长度
     */
    public void MergePass(int[] array, int gap, int length) {
        int i;
        // 归并gap长度的两个相邻子表
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }
        // 余下两个子表，后者长度小于gap
        if (i + gap - 1 < length) {
            merge(array, i, i + gap - 1, length - 1);
        }

        // 余下一个子表，长度小于gap，则不处理了
    }

    public int[] sort(int[] list) {
        for (int gap = 1; gap < list.length; gap = 2 * gap) {
            MergePass(list, gap, list.length);
            System.out.print("gap = " + gap + ":\t");
            System.out.println(Arrays.toString(list));
        }
        return list;
    }

    public static void main(String[] args) {
        int[] list = new int[]{9,1,5,3,4,2,6,8,7};
        new DivideAndConquerSort().sort(list);
    }

}
