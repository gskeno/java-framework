package com.gson.algo.leetcode.monotonicstack;

import com.gson.algo.leetcode.hot100.完全平方数_279;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/maximum-width-ramp/
 * 
 * 给定一个整数数组A，坡是元组(i, j)，其中i < j且A[i] <= A[j]。这样的坡的宽度为j - i。
 * 找出A中的坡的最大宽度，如果不存在，返回 0 。
 *
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 *
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 *
 */
public class 最大宽度坡 {

    /**
     * 输入：[6,0,8,2,1,5]
     * 输出：4
     * 解释：
     * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
     *
     * 重点在于找两个下标i, j。i和j的间距最大且nums[i] <= nums[j]。
     * 可以考虑对下标pos进行排序，nums[pos]值越小,pos越排在前面，得到一个下标组成的数组。
     * @param nums
     * @return
     */
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        // 数组的元素值范围为 0~n-1
        Integer[] posArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            posArr[i] = i;
        }
        Arrays.sort(posArr, (o1, o2) -> nums[o1] - nums[o2]);
        // 到了这里，知道i<j时, nums[posArr[i]] <= nums[posArr[j]]， [i, j]肯定是一个合法的坡，
        // 我们需要获取到宽度最大的坡。
        int ans = 0;
        // 维护一个递增栈
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < posArr.length; i++) {
            while (!stack.isEmpty() && posArr[i] < stack.peek()){
                stack.pop();
            }
            if (!stack.isEmpty()){
                ans = Math.max(ans, posArr[i] - stack.peekLast());
            }
            stack.push(posArr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        最大宽度坡 solution = new 最大宽度坡();
        int ans = 0;
        ans = solution.maxWidthRamp(new int[]{6,0,8,2,1,5});
        System.out.println(ans);

        ans = solution.maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1});
        System.out.println(ans);
    }
}
