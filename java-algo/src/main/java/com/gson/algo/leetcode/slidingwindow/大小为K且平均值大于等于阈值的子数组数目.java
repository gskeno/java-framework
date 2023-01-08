package com.gson.algo.leetcode.slidingwindow;

/**
 * 给你一个整数数组arr和两个整数 k和 threshold。
 *
 * 请你返回长度为 k且平均值大于等于threshold的子数组数目。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * 输出：3
 * 解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
 * 示例 2：
 *
 * 输入：arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * 输出：6
 * 解释：前 6 个长度为 3 的子数组平均值都大于 5 。注意平均值不是整数。
 * 
 *
 * 提示：
 *
 * 1 <= arr.length <= 105
 * 1 <= arr[i] <= 104
 * 1 <= k <= arr.length
 * 0 <= threshold <= 104
 *
 *
 */
public class 大小为K且平均值大于等于阈值的子数组数目 {
    /**
     * 提示1: 即长度为k的子数组元素之和要>= k * threshold
     * 提示2: 子数组元素和可以使用前缀数组
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        // 前n个元素和
        int[] preSum = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            preSum[i] = preSum[i-1] + arr[i-1];
        }
        int left = 0;
        int right = k - 1;
        int target = threshold * k;
        int ans = 0;
        while (right < n){
            int region = preSum[right + 1] - preSum[left];
            if (region >= target){
                ans++;
            }
            left++;
            right++;
        }

        return ans;
    }

    public static void main(String[] args) {
        大小为K且平均值大于等于阈值的子数组数目 solution = new 大小为K且平均值大于等于阈值的子数组数目();
        int ans = solution.numOfSubarrays(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4);
        System.out.println(ans);

        ans = solution.numOfSubarrays(new int[]{11,13,17,23,29,31,7,5,2,3}, 3, 5);
        System.out.println(ans);
    }
}
