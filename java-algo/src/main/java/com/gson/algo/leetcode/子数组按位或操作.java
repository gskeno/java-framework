package com.gson.algo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/bitwise-ors-of-subarrays/
 */
public class 子数组按位或操作 {

    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> res = new HashSet<>();
        Set<Integer> preAns = new HashSet<>();
        preAns.add(0);
        // 从左到右，遍历到nums[i]时，对于以nums[i-1]为结尾的子数组 或运算结果的集合preAns,
        // 每个元素都 或操作下当前元素nums[i], 再加上当前元素nums[i],
        // 这些放在一起赋给preAns。
        for (int i = 0; i < n; i++) {
            Set<Integer> ans = new HashSet<>();
            for(int j : preAns){
                ans.add(j | arr[i]);
            }
            ans.add(arr[i]);
            preAns = ans;
            // 以nums[i]为结尾的子数组的所有 或结果 加入到最终答案中去
            res.addAll(preAns);
        }
        return res.size();
    }

    public static void main(String[] args) {
        子数组按位或操作 solution = new 子数组按位或操作();
        int ans;
        ans = solution.subarrayBitwiseORs(new int[]{1,2,4});
        System.out.println(ans);
    }
}
