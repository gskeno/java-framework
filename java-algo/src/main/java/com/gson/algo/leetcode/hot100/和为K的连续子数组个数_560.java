package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/subarray-sum-equals-k/
 */
public class 和为K的连续子数组个数_560 {

    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k){
                ans++;
            }
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k){
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        和为K的连续子数组个数_560 solution = new 和为K的连续子数组个数_560();
        System.out.println(solution.subarraySum(new int[]{1,1,1}, 2));
        System.out.println(solution.subarraySum(new int[]{1,2,3}, 3));
    }
}
