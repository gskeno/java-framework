package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class 数组中的第K个最大元素_215 {

    // TODO
    public int findKthLargest(int[] nums, int K) {
        int heapSize = nums.length;

        initMaxHeap(nums);
        for(int i = 1; i<=K-1;i++){
            nums[0] = nums[heapSize-1];
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void initMaxHeap(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            maxHeapify(nums, i, nums.length);
        }
    }


    public void maxHeapify(int[] nums, int idx, int heapSize) {
        int largestIdx = idx;
        int largest = nums[idx];
        int leftIdx = 2 * idx + 1;
        // 这里有bug，可能会边界溢出
        int rightIdx = 2 * idx + 2;

        // 左节点比父节点大
        if (leftIdx < heapSize && nums[leftIdx] > largest) {
            largest = nums[leftIdx];
            largestIdx = leftIdx;
        }

        // 右节点比父节点更大
        if (rightIdx < heapSize && nums[rightIdx] > largest) {
            largest = nums[rightIdx];
            largestIdx = rightIdx;
        }

        // 存在左右子节点，比父亲大的情况
        if (largestIdx != idx) {
            // 交换
            swap(nums, idx, largestIdx);
            // 递归调整
            maxHeapify(nums, largestIdx, heapSize);
        }

    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

        数组中的第K个最大元素_215 solution = new 数组中的第K个最大元素_215();
        System.out.println(solution.findKthLargest(new int[]{3,2,1,5,6,4},2 ));
    }
}
