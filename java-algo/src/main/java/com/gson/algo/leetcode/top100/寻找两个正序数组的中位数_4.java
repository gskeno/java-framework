package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和数组 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 如果算法复杂度为O(m+n)，则很好处理，全部遍历一遍并排序，取"中间"数即可。
 */
public class 寻找两个正序数组的中位数_4 {

    /**
     * 寻找两个正序数组的第K(K从1算起)小的元素。
     * 如果两个数组元素个数和为奇数，则K值只有一个,为(count1+count2)/2 + 1, 比如共9个数，则中位数第5小的数。
     *
     * 如果两个数组元素个数和为偶数，则K值 有两个, 为(count1+count2)/2和 (count1+count2)/2+1, 比如共8个数，则中位数为第4和第5小的数。
     *
     * 如  1, 8, 12, 13, 18
     *     2, 3, 10, 11
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }
}
