package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class 上一个更小元素 {

    /**
     * 以数组[3,1,2,5,4]举例，
     * 3的上一个更小元素是-1(不存在)，
     * 1的上一个更大元素是-1，
     * 2的上一个更大元素是1，
     * 5的上一个更大元素是2，
     * 4的上一个更大元素是2。
     * 所以答案是[-1, -1, 1, 2, 2]。
     * @param nums
     * @return
     */
    public int[] previousSmallerNum(int[] nums){
        int[] ans = new int[nums.length];
        // 初始值为-1
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            // 维护一个单调不降栈
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
        上一个更小元素 solution = new 上一个更小元素();
        int[] ans;
        ans = solution.previousSmallerNum(new int[]{3, 1, 2, 5, 4});
        System.out.println(Arrays.toString(ans));
    }
}
