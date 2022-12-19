package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 */
public class 最长重复子数组 {

    /**
     * Use dynamic programming. dp[i][j] will be the longest common prefix of A[i:] and B[j:]
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int  m = nums1.length;
        int  n = nums2.length;
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int i = m-1; i >=0 ; i--) {
            for (int j = n-1; j >= 0 ; j--) {
                if (nums1[i] == nums2[j]){
                    if (i == m-1 || j == n-1){
                        dp[i][j] = 1;

                    }else {
                        dp[i][j] = dp[i+1][j+1] + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        最长重复子数组 solution = new 最长重复子数组();
        int[] nums1, nums2;
        int ans;
        nums1 = new int[]{1,2,3,2,1};
        nums2 = new int[]{3,2,1,4,7};
        ans = solution.findLength(nums1, nums2);
        System.out.println(ans);

        nums1 = new int[]{0,0,0,0,0};
        nums2 = new int[]{0,0,0,0,0};
        ans = solution.findLength(nums1, nums2);
        System.out.println(ans);
    }
}
