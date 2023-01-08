package com.gson.algo.leetcode.slidingwindow;

/**
 * https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/
 *
 * 给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 L 和 M。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）
 *
 * 从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) 并满足下列条件之一：
 *
 * 
 *
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 * 
 *
 * 示例 1：
 *
 * 输入：A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * 输出：20
 * 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
 * 示例 2：
 *
 * 输入：A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * 输出：29
 * 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
 * 示例 3：
 *
 * 输入：A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * 输出：31
 * 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
 */
public class 两个非重叠子数组的最大和 {

    /**
     * 提示1: 使用前缀和思想和滑动窗口，对于每一个长度为L的子数组，找到在其之前(或之后)出现的长度为M的子数组，计算其和作为备选答案，
     *       最大的备选答案值就是最终答案。
     * 提示2: 可以强制使L <= M， 简化分析，以短数组作为分析入口，长数组可以在其前也可以在其后
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // 使长度 L < M
        if (firstLen > secondLen){
            int temp = secondLen;
            secondLen = firstLen;
            firstLen = temp;
        }
        int n = nums.length;
        // preSum[i]表示前i个元素的和
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        // lp[i]表示[0, i]区间内长度为M的子数组的最大和
        int[] lp = new int[n];
        // mp[i]表示[i, n)区间内长度为M的子数组的最大和
        int[] mp = new int[n];
        for (int i = secondLen - 1; i < n; i++) {
            if (i == 0){
                lp[i] = nums[i];
            }else {
                lp[i] = Math.max(lp[i-1], preSum[i + 1] - preSum[i - secondLen + 1]);
            }
        }
        for (int i = n - secondLen; i >=0 ; i--){
            if (i == n - 1){
                mp[i] = nums[i];
            }else {
                mp[i] = Math.max(mp[i+1], preSum[i + secondLen] - preSum[i]);
            }
        }
        int ans = 0;
        for (int i = 0; i <= n - firstLen; i++) {
            int j = i + firstLen - 1;
            if ( i == 0){
                ans = Math.max(ans, mp[j + 1] + preSum[i + firstLen] - preSum[i]);
            }else if ( j == n - 1){
                ans = Math.max(ans, lp[i - 1] + preSum[i + firstLen] - preSum[i]);
            }else {
                ans = Math.max(ans, mp[j + 1] + preSum[i + firstLen] - preSum[i]);
                ans = Math.max(ans, lp[i - 1] + preSum[i + firstLen] - preSum[i]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        两个非重叠子数组的最大和 solution = new 两个非重叠子数组的最大和();
        int ans = 0;
        ans = solution.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2);
        System.out.println(ans == 20);

        ans = solution.maxSumTwoNoOverlap(new int[]{3,8,1,3,2,1,8,9,0}, 3, 2);
        System.out.println(ans == 29);

        ans = solution.maxSumTwoNoOverlap(new int[]{2,1,5,6,0,9,5,0,3,8}, 4, 3);
        System.out.println(ans == 31);
    }
}
