package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/
 */
public class 按位或最大的最小子数组长度 {

    public int[] smallestSubarrays2(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = 1;
            for(int j = i-1; j >=0 && (nums[j] |nums[i]) != nums[j]; j--){
                nums[j] |= nums[i];
                ans[j] = i -j + 1;
            }
        }
        return ans;
    }

    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        List<int[]> ors = new ArrayList<int[]>(); // 按位或的值 + 对应子数组的右端点的最小值
        for (int i = n - 1; i >= 0; --i) {
            ors.add(new int[]{0, i});
            int k = 0;
            for (int[] or : ors) {
                or[0] |= nums[i];
                if (ors.get(k)[0] == or[0])
                    ors.get(k)[1] = or[1]; // 合并相同值，下标取最小的
                else ors.set(++k, or);
            }
            // 会移除元素，使list容量变小
            ors.subList(k + 1, ors.size()).clear();
            // 本题只用到了 ors[0]，如果题目改成任意给定数值，可以在 ors 中查找
            ans[i] = ors.get(0)[1] - i + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        按位或最大的最小子数组长度 solution = new 按位或最大的最小子数组长度();
        int[] ans;
        // ans = solution.smallestSubarrays(new int[]{2,3,1});
        // ans = solution.smallestSubarrays(new int[]{1,0,2,1,3});
         ans = solution.smallestSubarrays(new int[]{8, 1});
        System.out.println(Arrays.toString(ans));

    }
}
