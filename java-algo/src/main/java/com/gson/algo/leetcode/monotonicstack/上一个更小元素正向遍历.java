package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class 上一个更小元素正向遍历 {

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
        // 单调递增栈
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] <= stack.peek()){
                stack.pop();
            }
            if (!stack.isEmpty()){
               ans[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        上一个更小元素正向遍历 solution = new 上一个更小元素正向遍历();
        int[] ans;
        ans = solution.previousSmallerNum(new int[]{3, 1, 2, 5, 4});
        System.out.println(Arrays.toString(ans));
    }
}
