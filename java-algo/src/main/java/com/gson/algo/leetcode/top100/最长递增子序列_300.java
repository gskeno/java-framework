package com.gson.algo.leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class 最长递增子序列_300 {

    /**
     * 贪心 + 二分法
     * 设置dp[i]表示最长递增子序列长度为i时的最小末尾元素
     * len表示当前最长的递增子序列长度
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // 如果当前元素比dp[last]大，直接追加到dp中,且最长递增子序列长度+1
            if (num > dp.get(dp.size() - 1)) {
                dp.add(num);
                len++;
            }
            // 如果当前元素小于等于dp[last], 则肯定存在一个递增子序列长度l， l在0到len之间，
            // 这个递增子序列的末尾元素可以被替代为num。
            // 如  nums = 1, 7, 10, 6
            //     dp   = 1, 7, 10,
            //     处理nums[3]=6这个元素时，从dp数组中找到第一个大于6的数字dp[k]>6，将其更换为6，即dp[k]=6
            //     表示子序列长度为k+1的递增子序列的最小末尾元素为6
            else {
                int firstLargerPos = getFirstLargerPos(dp, num);
                if (firstLargerPos == -1) {
                    continue;
                }
                if (firstLargerPos > 0 && dp.get(firstLargerPos - 1) == num) {
                    continue;
                }
                dp.set(firstLargerPos, num);

            }
        }
        return len;
    }

    /**
     * 从nums[1]开始 返回升序数组中第一个大于target的元素位置，如果没有，返回-1
     *
     * @param nums
     * @param target
     * @return
     */
    public int getFirstLargerPos(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        int pos = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums.get(mid) <= target) {
                left = mid + 1;
            } else {
                pos = mid;
                right = mid - 1;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        最长递增子序列_300 solution = new 最长递增子序列_300();
        System.out.println(solution.lengthOfLIS(new int[]{1,2,-10,-8,-7}));
        System.out.println(solution.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9}));
        System.out.println(solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(solution.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(solution.lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }
}
