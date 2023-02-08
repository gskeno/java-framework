package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 */
public class 柱状图中最大的矩形 {

    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        // 遍历每个元素nums[i]，找到左侧第一个小于nums[i]的下标，+ 1 作为left;
        // 找到右侧第一个小于nums[i]的下标, -1 作为right。
        // 则以当前元素nums[i]为高的最大矩形面积为 Si = (right - left + 1) * nums[i]
        // 最终答案就是最大的Si。
        Stack<Integer> stack = new Stack<>();
        int[] previousSmallerIdx = new int[heights.length];
        Arrays.fill(previousSmallerIdx, -1);
        //维护一个单调递增栈
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                stack.pop();
            }
            if (!stack.isEmpty()){
                previousSmallerIdx[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();

        int[] nextSmallerIdx = new int[heights.length];
        Arrays.fill(nextSmallerIdx, heights.length);
        // 维护一个单调递增栈
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                Integer pop = stack.pop();
                nextSmallerIdx[pop] = i;
            }
            stack.push(i);
        }
        for (int i = 0; i < heights.length; i++) {
            int S = heights[i] * (nextSmallerIdx[i] - 1 - previousSmallerIdx[i]);
            ans = Math.max(ans, S);
        }

        return ans;
    }

    public static void main(String[] args) {
        柱状图中最大的矩形 solution = new 柱状图中最大的矩形();
        int[] height ;
        int ans ;
        height = new int[]{2, 1, 5, 6, 2, 3};
        ans = solution.largestRectangleArea(height);
        System.out.println(ans);

        height = new int[]{2, 4};
        ans = solution.largestRectangleArea(height);
        System.out.println(ans);
    }
}
