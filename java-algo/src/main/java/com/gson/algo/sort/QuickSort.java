package com.gson.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排
 *
 * 数组int[] A中每次随机挑选一个元素b，将比b小的元素放到b的左侧形成序列C，比b大的元素放到b的右侧形成序列D。
 * 对C，D分别进行上述操作，直至序列中只有一个元素。
 */
public class QuickSort {

    private int[] sortArray(int[] nums){
        quickSort(nums, 0, nums.length -1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end){
        if (start < end){
            // 转点，支点
            int pivotIndex = partition(nums, start, end);
            quickSort(nums, start, pivotIndex-1);
            quickSort(nums, pivotIndex + 1, end);
        }
    }

    /**
     * [4,1,5,3,6,2,7,8]
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition(int[] nums, int start, int end){
        // 挑选的随机数下标位置为 start 到 end
        int randomIndex = new Random().nextInt(end - start + 1) + start;

        // 将选中的随机数与数组的最后一个元素互调位置
        // 调动后，数组变为 [4,1,5,8,6,2,7,3]
        swap(nums, randomIndex, end);

        // 慢指针初始值放在快指针-1的位置
        int slowPointer = start - 1;
        int quickPointer = start;

        for (; quickPointer < end; quickPointer++) {
            if (nums[quickPointer] < nums[end]){
                slowPointer ++;
                swap(nums, slowPointer, quickPointer);
                // 执行swap后, slowPoint永远 指向 数组中已经遍历的数组元素中最后一个小于nums[end]的数
            }
        }
        // ++ 后, slowPointer指向已经遍历的数组元素中第一个大于nums[end]的数
        slowPointer++;
        // swap后, nums[slowPointer]左边的数都小于当前值，右边的数都大于当前值
        swap(nums, slowPointer, quickPointer);

        return slowPointer;
    }

    /**
     * 交换指定位置元素
     * @param nums
     * @param index1
     * @param index2
     */
    private void swap(int[] nums, int index1, int index2){
        if (index1 != index2){
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {4,1,5,3,6,2,7,8};
        quickSort.sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
