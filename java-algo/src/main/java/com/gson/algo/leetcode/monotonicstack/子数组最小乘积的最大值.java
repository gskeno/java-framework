package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/maximum-subarray-min-product/
 */
public class 子数组最小乘积的最大值 {
    /**
     * 提示1: 求前一个更小元素，下标left
     * 提示2: 求后一个更小元素，下标right
     * 提示3: 以当前元素i 为最小值的子数组最小乘积为 nums[i] * sum{nums[left+1], .... nums[right-1]}
     *
     * @param nums
     * @return
     */
    public int maxSumMinProduct(int[] nums) {
        Stack<Integer> stack1 = new Stack<>();
        int[] previousSmallerNums = new int[nums.length];
        Arrays.fill(previousSmallerNums, -1);

        Stack<Integer> stack2 = new Stack<>();
        int[] nextSmallerNums = new int[nums.length];
        Arrays.fill(nextSmallerNums, -1);
        // 前缀和数组
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            // 不降栈求上一个更小元素，存储元素下标
            while (!stack1.isEmpty() && nums[i] <= nums[stack1.peek()]) {
                stack1.pop();
            }
            if (!stack1.isEmpty()) {
                // 上一个更小元素
                previousSmallerNums[i] = stack1.peek();
            }
            stack1.push(i);

            // 下一个更小元素
            while (!stack2.isEmpty() && nums[i] < nums[stack2.peek()]){
                Integer pop = stack2.pop();
                nextSmallerNums[pop] = i;
            }
            stack2.push(i);

            preSum[i + 1] = preSum[i] + nums[i];
        }

        long max = 0;
        for (int i = 0; i < nums.length; i++) {
            int L = previousSmallerNums[i] + 1;
            int R = nextSmallerNums[i] == -1 ? nums.length - 1 : nextSmallerNums[i] - 1;
            long sum = (preSum[R + 1] - preSum[L]) * nums[i];
            max = Math.max(max, sum);
        }
        return (int)(max % (10_0000_0000 + 7));
    }

    public static void main(String[] args) {
        子数组最小乘积的最大值 solution = new 子数组最小乘积的最大值();
        int ans = 0;
        ans = solution.maxSumMinProduct(new int[]{2,3,3,1,2});
        System.out.println(ans);

        ans = solution.maxSumMinProduct(new int[]{1,2,3,2});
        System.out.println(ans);

        ans = solution.maxSumMinProduct(new int[]{3,1,5,6,4,2});
        System.out.println(ans);
    }
}
