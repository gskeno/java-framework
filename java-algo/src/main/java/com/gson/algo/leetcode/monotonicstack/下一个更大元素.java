package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class 下一个更大元素 {

    /**
     * 下一个更大元素，当某元素后没有更大的元素时，认为下一个更大元素为-1;
     * 比如 [3,1,2,5,4]的下一个更大元素是[5,2,5,-1,-1]
     *
     * 使用单调不升栈，栈中存储元素的下标；
     * 当遍历到的元素nums[i] <= nums[栈顶元素]时，将i压入栈中;
     * 否则，将栈顶元素值(下标索引)弹出栈，直至栈为空，或者 nums[i] <= nums[栈顶元素]。
     *
     * 需要注意的是:
     * 1. 弹出栈顶元素时，便知道栈顶元素(数组下标)后的下一个更大元素是当前遍历到的元素nums[i]
     * 2. 遍历结束时，如果栈中还有元素，表示栈中的元素(数组下标)后没有更大的元素了。
     * @param nums
     * @return
     */
    public int[] nextLargerNum(int[] nums){
        int[] ans = new int[nums.length];
        // 初始值为-1
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
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
        下一个更大元素 solution = new 下一个更大元素();
        int[] ans;
        ans = solution.nextLargerNum(new int[]{3, 1, 2, 5, 4});
        System.out.println(Arrays.toString(ans));
    }
}
