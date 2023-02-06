package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class 上一个更大元素 {

    /**
     * 以数组[3,1,2,5,4]举例，
     * 3的上一个更大元素是-1(不存在)，
     * 1的上一个更大元素是3，
     * 2的上一个更大元素是3，
     * 5的上一个更大元素是-1(不存在），
     * 4的上一个更大元素是5。
     * 所以答案是[-1, 3, 3, -1, 5]。
     * @param nums
     * @return
     */
    public int[] previousLargerNum(int[] nums){
        int[] ans = new int[nums.length];
        // 初始值为-1
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            // 维护一个单调不升栈
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                Integer top = stack.pop();
                ans[top] = nums[i];
            }
            // 存储的是元素下标
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        上一个更大元素 solution = new 上一个更大元素();
        int[] ans;
        ans = solution.previousLargerNum(new int[]{3, 1, 2, 5, 4});
        System.out.println(Arrays.toString(ans));
    }
}
