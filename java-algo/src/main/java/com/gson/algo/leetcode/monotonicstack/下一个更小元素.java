package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class 下一个更小元素 {

    /**
     * 维护一个单调不降栈,当某元素后没有更小的元素时，认为下一个更小元素为-1;
     * 比如 [3,1,2,5,4]的下一个更小元素是[1,-1,-1,4,-1]
     * @param nums
     * @return
     */
    public int[] nextLargerNum(int[] nums){
        int[] ans = new int[nums.length];
        // 初始值为-1
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            // 单调不降栈
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]){
                Integer top = stack.pop();
                ans[top] = nums[i];
            }
            // 存储的是元素下标
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        下一个更小元素 solution = new 下一个更小元素();
        int[] ans;
        ans = solution.nextLargerNum(new int[]{3, 1, 2, 5, 4});
        System.out.println(Arrays.toString(ans));
    }
}
