package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/global-and-local-inversions/
 * <p>
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 * <p>
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 * <p>
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 * <p>
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 * <p>
 * 输入：nums = [1,0,2]
 * 输出：true
 * 解释：有 1 个全局倒置，和 1 个局部倒置
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：false
 * 解释：有 2 个全局倒置，和 1 个局部倒置。
 * <p>
 * n == nums.length
 * 1 <= n <= 105
 * 0 <= nums[i] < n
 * nums 中的所有整数 互不相同
 * nums 是范围 [0, n - 1] 内所有数字组成的一个排列
 */
public class 全局倒置与局部倒置 {

    /**
     * 维护后缀最小值
     *
     * 局部倒置 一定是 全局倒置，因此找到一个全局倒置不是局部倒置，则返回false；否则，返回true。
     *
     * 找到一个全局倒置(非局部倒置)等价于 对于 i < j, 找到nums[i] > nums[j]，且j != i+1。
     * 容易二次遍历解决问题。但时间复杂度高，需要优化。
     *
     * 如果nums[i] > min{nums[i+2], nums[i+3], nums[n-1]}，则返回false。否则返回true。
     * 维护一个倒序最小值minSuffix即可
     *
     * @param nums
     * @return
     */
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int minSuffix = nums[n-1];
        for (int i = n-3; i >= 0 ; i--) {
            if (nums[i] > minSuffix){
                return false;
            }
            minSuffix = Math.min(minSuffix, nums[i+1]);
        }
        return true;
    }

    public static void main(String[] args) {
        全局倒置与局部倒置 solution = new 全局倒置与局部倒置();
        System.out.println(solution.isIdealPermutation(new int[]{1,0,2}));
        System.out.println(solution.isIdealPermutation(new int[]{0,2,1}));
        System.out.println(solution.isIdealPermutation(new int[]{1,2,0}));
        System.out.println(solution.isIdealPermutation(new int[]{2,0,1}));
        System.out.println(solution.isIdealPermutation(new int[]{2,1,0}));
        System.out.println(solution.isIdealPermutation(new int[]{0,2,3,1}));
    }
}
