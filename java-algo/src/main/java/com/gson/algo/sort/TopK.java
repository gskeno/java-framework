package com.gson.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class TopK {

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

    /**
     * 对nums数组进行分区，假设返回值为index,则分区后,
     * 下标小于index的元素都比nums[index]小
     * 下标大于index的元素都比nums[index]大
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

    public int findKthLargest(int[] nums, int k){
        int target = nums.length - k;
        int start = 0;
        int end = nums.length-1;

        int index = partition(nums, start, end);

        while (index != target){
            // 位置在 index 后面的元素都比nums[index]大，但是这样的元素不足k个，所以要找的第K大数字在index位置前面
            if (index > target){
                end = index - 1;
            }else {
                // 位置在 index 后面的元素都比nums[index]大，但是这样的元素多于k个，所以要找的第K大数字在index位置后面
                start = index + 1;
            }
            index = partition(nums, start, end);
        }

        return nums[index];
    }

    public static void main(String[] args) {
        int[] nums = {1,12,8,  7,  9,10,13,11};
        TopK topK = new TopK();
        int kthLargest = topK.findKthLargest(nums, 2);
        System.out.println(kthLargest);
    }
}
