package com.gson.algo.leetcode;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
 * leetcode第4题
 * 寻找两个有序数组的中位数
 */
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        // 有序数组元素个数之和为奇数个
        if (totalLength % 2 == 1){
            // 中位数只有一个
            int midIndex = totalLength /2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        }
        // 有序数据元素个数为偶数个
        else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    /**
     * 寻找第k小的元素
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private int getKthElement(int[] nums1, int[] nums2, int k){
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        int kthElement = 0;

        while (true){
            // 边界情况
            if (index1 == length1){
                return nums2[index2 + k - 1];
            }
            if (index2 == length2){
                return nums1[index1 + k -1];
            }

            // 第1小的元素，nums1中剩余有效
            if (k == 1){
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k /2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1 ;
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2){
                k-= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            }else {
                k-= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] nums1 = new int[]{1,3,4,9};
        int[] nums2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        double median = findMedianSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }
}
