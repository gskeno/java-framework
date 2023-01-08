package com.gson.algo.leetcode.slidingwindow;

/**
 * 给你一个整数数组arr 和一个整数值target。
 *
 * 请你在 arr中找 两个互不重叠的子数组且它们的和都等于target。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 *
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,2,4,3], target = 3
 * 输出：2
 * 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
 * 示例 2：
 *
 * 输入：arr = [7,3,4,7], target = 7
 * 输出：2
 * 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
 * 示例 3：
 *
 * 输入：arr = [4,3,2,6,2,3,4], target = 6
 * 输出：-1
 * 解释：我们只有一个和为 6 的子数组。
 * 示例 4：
 *
 * 输入：arr = [5,5,4,4,5], target = 3
 * 输出：-1
 * 解释：我们无法找到和为 3 的子数组。
 * 示例 5：
 *
 * 输入：arr = [3,1,1,1,5,1,2,1], target = 3
 * 输出：3
 * 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 * 
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 
 */
public class 找两个和为目标值且不重叠的子数组 {

    /**
     * 提示1: 设置dp[i]为nums[0]到nums[i]这个窗口区间范围内的满足题目要求的子数组的最小长度。
     *       子数组在这个窗口内即可，比如 nums=[1,2,3,4,5], target为5,则dp[3] = 2,
     *       因为[2,3]子数组满足和为5，且数组长度2最小。
     *       遍历i, 当以nums[i]结尾(含)的子数组满足和为target 条件时,
     *       dp[i] = i - j + 1; 特别地，当dp[i-1] >0 时, 可以再次尝试缩小dp[i]，如下
     *       dp[i] = min{dp[i], dp[i-1]}
     *
     * 提示2: 当窗口[left, right] 满足元素之和等于target时，dp[left-1] + (right - left + 1)就是当前满足条件的
     *        两个子数组长度和。遍历过程中，维护其最小值，最小值就是答案。
     *
     *
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int left = 0, right = 0;
        int[] dp = new int[n];
        int region = 0;
        int ans = Integer.MAX_VALUE;
        while (right < n){
            region += arr[right];

            while (region > target){
                region -= arr[left];
                left++;
            }

            if (region == target){
                dp[right] = right - left + 1;
                // [0, right-1] 区间内存在子数组满足和为target
                if (right > 0 && dp[right - 1] > 0){
                    dp[right] = Math.min(dp[right], dp[right - 1]);
                }
                // 表示前面已经有一个子数组满足和为target，且与当前数组不重叠
                if (left >= 1 && dp[left - 1] != 0){
                    ans = Math.min(dp[left - 1] + (right - left + 1), ans);
                }
            }else if (right > 0){
                dp[right] = dp[right -1 ];
            }


            if (right > 0 && dp[right - 1] > 0){
                    if (!(dp[right] <= dp[right - 1] && dp[right] != 0)){
                        System.out.println("dp[" + right + "]=" + dp[right] + ",dp[" + (right - 1) + "]=" + dp[right - 1]);
                        throw new RuntimeException();
                    }
            }
            right ++;

        }


        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        找两个和为目标值且不重叠的子数组 solution = new 找两个和为目标值且不重叠的子数组();
        int ans = 0;

        ans = solution.minSumOfLengths(new int[]{1,1,1,2,2,2,4,4}, 6);
        System.out.println(ans == 6);

        ans = solution.minSumOfLengths(new int[]{3,2,2,4,3}, 3);
        System.out.println(ans == 2);

        ans = solution.minSumOfLengths(new int[]{7,3,4,7}, 7);
        System.out.println(ans == 2);

        ans = solution.minSumOfLengths(new int[]{4,3,2,6,2,3,4}, 6);
        System.out.println(ans == -1);

        ans = solution.minSumOfLengths(new int[]{5,5,4,4,5}, 3);
        System.out.println(ans == -1);

        ans = solution.minSumOfLengths(new int[]{3,1,1,1,5,1,2,1}, 3);
        System.out.println(ans == 3);
    }
}
