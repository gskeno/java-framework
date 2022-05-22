package com.gson.algo.bit;

/**
 * https://leetcode.cn/problems/WGki4K/
 */
public class 只出现一次的数字 {
    /**
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     */

    public int singleNumber(int[] nums) {
        // ans表示answer
        int ans = 0;
        // int有32个位，出现3次的数字x，如果其二进制第i位(i从0算起)为0，或者为1，那么出现3次后，第i位为1的次数永远都是3的倍数
        for (int i = 0; i < 32; i++) {
            int temp = 0;
            // 所有数字二进制格式下第i位值都加起来
            for(int num : nums){
                temp += num >>i & 1;
            }

            ans += (temp%3) << i;
        }
        return ans;
    }

    public static void main(String[] args) {
        只出现一次的数字 solution = new 只出现一次的数字();
        int ans = solution.singleNumber(new int[]{2, 2, 2, 3});
        System.out.println(ans);
    }
}
