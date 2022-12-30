package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/longest-nice-subarray/
 * 给你一个由 正 整数组成的数组 nums 。
 *
 * 如果 nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。
 *
 * 返回 最长 的优雅子数组的长度。
 *
 * 子数组 是数组中的一个 连续 部分。
 *
 * 注意：长度为 1 的子数组始终视作优雅子数组。
 *
 * 输入：nums = [1,3,8,48,10]
 * 输出：3
 * 解释：最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
 * - 3 AND 8 = 0
 * - 3 AND 48 = 0
 * - 8 AND 48 = 0
 * 可以证明不存在更长的优雅子数组，所以返回 3 。
 *
 * 输入：nums = [3,1,5,11,13]
 * 输出：1
 * 解释：最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */
public class 最长优雅子数组 {

    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int left = 0;
        int right = 0;
        long res = nums[0];
        for (int i = 1; i < n ; i++) {
            // 说明当前元素与当前窗口内元素不友好
            if ((res & nums[i]) != 0){
                ans = Math.max(ans, right - left + 1);
                int j = getUnNiceIdx(nums, left, right, nums[i]);
                left = j + 1;
                right = i;
                res = sum(nums, left, right);
            }else {
                right = i;
                res += nums[i];
                ans = Math.max(ans, right - left + 1);
            }
        }
        return ans;
    }

    private long sum(int[] nums, int i, int j){
        long sum = 0;
        for (int k = i; k <=j ; k++) {
            sum += nums[k];
        }
        return sum;
    }

    /**
     * 寻找最后一个不友好的位置
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int getUnNiceIdx(int[] nums, int left, int right, int target){
        for (int i = right; i >= left ; i--) {
            if ((target & nums[i]) != 0){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        最长优雅子数组 solution = new 最长优雅子数组();
        System.out.println(solution.longestNiceSubarray(new int[]{1,3,8,48,10}));
        System.out.println(solution.longestNiceSubarray(new int[]{3,1,5,11,13}));
    }
}
