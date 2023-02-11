package com.gson.algo.leetcode.monotonicstack;

/**
 * https://leetcode.cn/problems/sum-of-subarray-ranges/
 */
public class 子数组范围和 {

    /**
     * M[i,j]表示区间i到j之间到最大值
     * N[i,j]表示区间i到j之间的最小值
     *
     * 对于所有的i，遍历j(j>i)，将M[i,j]- N[i,j]加到结果sum中
     * @param nums
     * @return
     */
    public long subArrayRanges(int[] nums) {
        int len = nums.length;
        int[][] M = new int[len][len];
        int[][] N = new int[len][len];
        long sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (j == i){
                    M[i][j] = nums[j];
                    N[i][j] = nums[j];
                }else {
                    M[i][j] = Math.max(M[i][j-1], nums[j]);
                    N[i][j] = Math.min(N[i][j-1], nums[j]);
                    sum += M[i][j] - N[i][j];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        子数组范围和 solution = new 子数组范围和();
        long ans = 0;
        ans = solution.subArrayRanges(new int[]{1,2,3});
        System.out.println(ans);

        ans = solution.subArrayRanges(new int[]{4,-2,-3,4,1});
        System.out.println(ans);

    }
}
