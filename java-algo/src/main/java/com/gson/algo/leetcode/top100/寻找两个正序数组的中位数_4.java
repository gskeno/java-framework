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
        // 寻找第k(k从1算起)小的数
        int k = (nums1.length  + nums2.length ) /2 + 1;
        // 从数组1的哪个下标位置元素开始分析
        int beginPos1 = 0;
        // 从数组2的哪个下标位置元素开始分析
        int beginPos2 = 0;
        while (true){
            int width = k/2;
            // 数组1从beginPos1开始的前k/2个数的最后一个
            int segEnd1 = nums1[beginPos1 + width - 1];
            // 数组2从beginPos2开始的前k/2个数的最后一个
            int segEnd2 = nums2[beginPos2 + width - 1];

            // segEnd1前的k/2-1个数都比segEnd2小，肯定不是中位数，后面将不再考虑(segEnd1也不考虑)
            if (segEnd1 < segEnd2){
                beginPos1 = beginPos1 + width;
                k = k - k/2;
            }
            // segEnd2前的k/2-1个数都比segEnd1小，肯定不是中位数，后面将不再考虑(segEnd2也不考虑)
            else if (segEnd1 > segEnd2){
                beginPos2 = beginPos2 + width;
                k = k - k/2;
            }
            // segEnd1, segEnd2前的共计k-2个数都比segEnd1=segEnd2小，后面都不再考虑
            else {

            }
        }

        return null;
    }
}
