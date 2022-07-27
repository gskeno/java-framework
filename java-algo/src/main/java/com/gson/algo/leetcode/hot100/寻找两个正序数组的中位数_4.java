package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和数组 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 如果算法复杂度为O(m+n)，则很好处理，全部遍历一遍并排序，取"中间"数即可。
 */
public class 寻找两个正序数组的中位数_4 {

    /**
     * 寻找两个正序数组的第K(K从1算起)小的元素。
     * 如果两个数组元素个数和为奇数，则K值只有一个,为(count1+count2)/2 + 1, 比如共9个数，则中位数第5小的数。
     * <p>
     * 如果两个数组元素个数和为偶数，则K值 有两个, 为(count1+count2)/2和 (count1+count2)/2+1, 比如共8个数，
     * 则中位数为第4和第5小的数的平均数。
     * <p>
     * 如  1, 8, 12, 13, 18
     * 2, 3, 10, 11
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保证第一个数组元素不多于第二个数组元素
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // 寻找第k(k从1算起)小的数
        int k = (nums1.length + nums2.length) / 2 + 1;
        boolean sumCountIsEven = (nums1.length + nums2.length) % 2 == 0;
        // 如果有偶数个元素，则目标是找到两个中间元素的较小元素，第二个元素就很好找了
        if (sumCountIsEven) {
            k = k - 1;
        }
        // 从数组1的哪个下标位置元素开始分析
        int beginPos1 = 0;
        // 从数组2的哪个下标位置元素开始分析
        int beginPos2 = 0;
        while (true) {
            // 说明数组1已经遍历完毕，且中位数不在数组1中
            if (beginPos1 >= nums1.length) {
                // 中位数是数组2从beginPos2下标开始的第k小的元素
                return sumCountIsEven ? (double) (nums2[beginPos2 + k - 1] + nums2[beginPos2 + k]) / 2 : nums2[beginPos2 + k - 1];
            }
            // 找第1小
            if (k == 1) {
                if (!sumCountIsEven) {
                    return Math.min(nums1[beginPos1], nums2[beginPos2]);
                } else {
                    return (double) getSmallestTwoSum(nums1, beginPos1, nums2, beginPos2) / 2;
                }
            }
            int width = k / 2;
            int range1 = width;
            int range2 = width;
            // 数组1从beginPos1开始的前width = k/2个数的最后一个元素
            int segEndIndex1 = beginPos1 + width - 1;
            // 数组2从beginPos2开始的前width = k/2个数的最后一个元素
            int segEndIndex2 = beginPos2 + width - 1;
            // 发生了越界
            if (segEndIndex1 >= nums1.length) {
                segEndIndex1 = nums1.length - 1;
                range1 = segEndIndex1 - beginPos1 + 1;
                range2 = k - range1;
                segEndIndex2 = beginPos2 + range2 - 1;
            }

            int segEnd1 = nums1[segEndIndex1];
            // 数组2从beginPos2开始的前k/2个数的最后一个
            int segEnd2 = nums2[segEndIndex2];

            // segEnd1前的range1-1个数都比segEnd2小，它们肯定不是中位数，后面将不再考虑(segEnd1也不考虑)
            if (segEnd1 < segEnd2) {
                beginPos1 = segEndIndex1 + 1;
                k = k - range1;
            }
            // segEnd2前的range2-1个数都比segEnd1小，它们肯定不是中位数，后面将不再考虑(segEnd2也不考虑)
            else if (segEnd1 > segEnd2) {
                beginPos2 = segEndIndex2 + 1;
                k = k - range2;
            }
            // segEnd1,以及之前的数都不再考虑, segEnd2前的数都不再考虑， segEnd2要考虑。
            else {
                beginPos1 = segEndIndex1 + 1;
                beginPos2 = segEndIndex2;
                k = k - range1 - (range2 - 1);

            }
        }
    }

    private int getSmallestTwoSum(int[] nums1, int index1, int[] nums2, int index2) {
        int min1 = nums1[index1] <= nums2[index2] ? nums1[index1] : nums2[index2];
        int min2 = nums1[index1] >= nums2[index2] ? nums1[index1] : nums2[index2];
        if (index1 + 1 <= nums1.length - 1) {
            min2 = Math.min(min2, nums1[index1 + 1]);
        }
        if (index2 + 1 <= nums2.length - 1){
            min2 = Math.min(min2, nums2[index2 + 1]);
        }
        return min1 + min2;
    }

    public static void main(String[] args) {
        寻找两个正序数组的中位数_4 solution = new 寻找两个正序数组的中位数_4();
//        // 总共奇数个元素
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        //数组1共2个元素，数组2共7个元素，中位数是有序数组(11个元素)的第5个元素，数组1会先遍历完
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4, 5, 7, 9, 11, 13}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 6}, new int[]{2, 4, 5, 7, 9, 11, 13}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 8}, new int[]{2, 4, 5, 7, 9, 11, 13}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5,6,7}));
//
        // 总共偶数个元素
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{2,2,4,4}, new int[]{2,2,4,4}));

    }
}
