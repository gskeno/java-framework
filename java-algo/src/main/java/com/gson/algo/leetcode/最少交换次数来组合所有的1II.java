package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii/
 *
 * 交换 定义为选中一个数组中的两个 互不相同 的位置并交换二者的值。
 *
 * 环形 数组是一个数组，可以认为 第一个 元素和 最后一个 元素 相邻 。
 *
 * 给你一个 二进制环形 数组 nums ，返回在 任意位置 将数组中的所有 1 聚集在一起需要的最少交换次数。
 *
 * 输入：nums = [0,1,0,1,1,0,0]
 * 输出：1
 * 解释：这里列出一些能够将所有 1 聚集在一起的方案：
 * [0,0,1,1,1,0,0] 交换 1 次。
 * [0,1,1,1,0,0,0] 交换 1 次。
 * [1,1,0,0,0,0,1] 交换 2 次（利用数组的环形特性）。
 * 无法在交换 0 次的情况下将数组中的所有 1 聚集在一起。
 * 因此，需要的最少交换次数为 1 。
 *
 *
 */
public class 最少交换次数来组合所有的1II {

    public int minSwaps(int[] nums) {
        // 滑动窗口的大小
        int ones = Arrays.stream(nums).sum();
        int ans = Integer.MAX_VALUE;
        // 实时维护窗口内1的个数
        int oneCount = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            // 一直扩大右边界，使|j-i| = 窗口大小
            while (j - i < ones){
                oneCount += nums[j % nums.length];
                j++;
            }
            ans = Math.min(ans, ones - oneCount);
            oneCount -= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        最少交换次数来组合所有的1II solution = new 最少交换次数来组合所有的1II();
        System.out.println(solution.minSwaps(new int[]{0,1,0,1,1,0,0}));
        System.out.println(solution.minSwaps(new int[]{0,1,1,1,0,0,1,1,0}));
        System.out.println(solution.minSwaps(new int[]{1,1,0,0,1}));
    }
}
