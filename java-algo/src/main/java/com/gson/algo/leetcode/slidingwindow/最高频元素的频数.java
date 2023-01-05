package com.gson.algo.leetcode.slidingwindow;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/frequency-of-the-most-frequent-element/
 * 
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 *
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 *
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= k <= 105
 *
 */
public class 最高频元素的频数 {

    /**
     * 滑动窗口与前缀和
     * 提示1: 数组排序
     * 提示2: 维护一个满足条件的窗口，[left, right]，则肯定存在left到right的元素求和sum + k >= (right-left + 1) * nums[right]，
     *       这里可以借助前缀和思想。在满足要求的基础上，不断滑动右边界，不满足要求时，再滑动左边界，一直如此操作，直至右边界滑动到
     *       数组末尾，记录滑动过程中出现的最高频次
     * 提示3: 前缀和使用long来表示，因为可能超出int最大值
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        // preSum[i]表示nums数组中前i个数的和, 前缀和。
        // 注意，是前i个元素的和
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n ; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < n){
            // 窗口右指针向右滑动
            if (preSum[right+1] - preSum[left] + k >= (right - left + 1) * nums[right]){
                ans = Math.max(ans, right - left + 1);
                right++;
            }
            // 窗口左指针向右滑动
            else {
                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        最高频元素的频数 solution = new 最高频元素的频数();
        int ans = 0;
        ans = solution.maxFrequency(new int[]{1,2,4}, 5);
        System.out.println(ans);

        ans = solution.maxFrequency(new int[]{1,4,8,13}, 5);
        System.out.println(ans);

        ans = solution.maxFrequency(new int[]{3,9,6}, 2);
        System.out.println(ans);
    }
}
