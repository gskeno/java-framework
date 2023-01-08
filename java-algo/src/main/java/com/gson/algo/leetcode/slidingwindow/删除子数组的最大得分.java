package com.gson.algo.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/maximum-erasure-value/
 *
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
 *
 * 返回 只删除一个 子数组可获得的 最大得分 。
 *
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是a 的一个子数组。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,4,5,6]
 * 输出：17
 * 解释：最优子数组是 [2,4,5,6]
 * 示例 2：
 *
 * 输入：nums = [5,2,1,2,5,2,1,2,5]
 * 输出：8
 * 解释：最优子数组是 [5,2,1] 或 [1,2,5]
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 
 */
public class 删除子数组的最大得分 {

    /**
     * 提示1: 本质是动态维护一个不含重复元素的子数组窗口，最大的窗口内元素值之和就是答案。
     * @param nums
     * @return
     */
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int region = 0;
        int ans = 0;
        Map<Integer, Integer> numPos = new HashMap<>();
        while (right < n){
            Integer num = nums[right];
            // num第一次出现在滑动窗口中
            if (numPos.get(num) == null){
                region += num;
                ans = Math.max(ans, region);
                numPos.put(num, right);
            }
            // num在之前出现过，需要将其从窗口中移除出去，再把num追加到窗口中
            else {
                boolean hitNum = false;
                while (!hitNum){
                    region -= nums[left];
                    numPos.remove(nums[left]);
                    if (nums[left] == num){
                        hitNum = true;
                    }
                    left++;
                }
                region += num;
                numPos.put(num, right);
            }
            right ++;
        }
        return ans;
    }

    public static void main(String[] args) {
        删除子数组的最大得分 solution = new 删除子数组的最大得分();
        int ans = 0;
        ans = solution.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6});
        System.out.println(ans == 17);

        ans = solution.maximumUniqueSubarray(new int[]{5,2,1,2,5,2,1,2,5});
        System.out.println(ans == 8);
    }
}
