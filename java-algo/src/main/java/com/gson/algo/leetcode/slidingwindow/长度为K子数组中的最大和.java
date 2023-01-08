package com.gson.algo.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k/
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：
 * <p>
 * 子数组的长度是 k，且
 * 子数组中的所有元素 各不相同 。
 * 返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。
 * <p>
 * 子数组 是数组中一段连续非空的元素序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,4,2,9,9,9], k = 3
 * 输出：15
 * 解释：nums 中长度为 3 的子数组是：
 * - [1,5,4] 满足全部条件，和为 10 。
 * - [5,4,2] 满足全部条件，和为 11 。
 * - [4,2,9] 满足全部条件，和为 15 。
 * - [2,9,9] 不满足全部条件，因为元素 9 出现重复。
 * - [9,9,9] 不满足全部条件，因为元素 9 出现重复。
 * 因为 15 是满足全部条件的所有子数组中的最大子数组和，所以返回 15 。
 * 示例 2：
 * <p>
 * 输入：nums = [4,4,4], k = 3
 * 输出：0
 * 解释：nums 中长度为 3 的子数组是：
 * - [4,4,4] 不满足全部条件，因为元素 4 出现重复。
 * 因为不存在满足全部条件的子数组，所以返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class 长度为K子数组中的最大和 {

    /**
     * 提示1: 动态维护一个长度为k的窗口，及窗口内元素最后一次出现位置的map, num-> pos。
     * 提示2: 对于任意一个大小为k的窗口，map元素个数<k时，表示窗口内右重复元素，不考虑其元素和。
     * 提示3: 移除窗口左侧元素时，如果map.get(nums[left]) != left，表示左侧元素num在窗口内右侧还有，map不移除元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> numPos = new HashMap<>();

        long region = 0;
        for (; right < k; right++) {
            numPos.put(nums[right], right);
            region += nums[right];
        }
        long ans = numPos.size()<k ? 0 : region;
        while (right < n) {
            region += nums[right];
            region -= nums[left];

            numPos.put(nums[right], right);
            // 窗口左侧元素只在窗口内出现一次时，才能从map中移除，
            // 因为map记录的是元素最后出现的位置。
            if (numPos.get(nums[left]) == left){
                numPos.remove(nums[left]);
            }

            if (numPos.size() == k){
                ans = Math.max(ans, region);
            }
            // 每次循环，窗口左右边界都会向右移动一步
            left++;
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        长度为K子数组中的最大和 solution = new 长度为K子数组中的最大和();
        long ans = 0;
        ans = solution.maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3);
        System.out.println(ans == 15);

        ans = solution.maximumSubarraySum(new int[]{4,4,4}, 3);
        System.out.println(ans == 0);
    }
}
