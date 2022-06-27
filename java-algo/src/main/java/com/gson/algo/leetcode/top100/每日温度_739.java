package com.gson.algo.leetcode.top100;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/daily-temperatures/
 * <p>
 * 给定一个整数数组temperatures，表示每天的温度，
 * 返回一个数组answer，其中answer[i]是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用0 来代替。
 */
public class 每日温度_739 {

    public int[] dailyTemperatures(int[] temperatures) {
        return dailyTemperatures1(temperatures);
    }

    /**
     * 暴力
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (temperatures[j] > temperatures[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        int endIndex = len - 1;
        for (int i = 0; i < len; i++) {
            // 当i与endIndex重叠时，要从i+1一直遍历到最后一个元素，找到第一个大于nums[i]的数
            if (i == endIndex) {
                endIndex = len - 1;
            }
            for (int j = i + 1; j <= endIndex; j++) {
                if (temperatures[j] > temperatures[i]) {
                    ans[i] = j - i;
                    // 因为nums[endIndex]肯定会比nums[i+1]大的，所以下一次内循环到endIndex为止就可以了
                    endIndex = j;
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 单调栈
     *
     * 从栈底到栈顶的元素依次递减，则称之为递减栈
     * 设stack栈存储数组 temperatures 元素的下标。
     *
     * 遍历temperatures数组，每遍历到temperatures[i]时，
     * 1. 如果此时栈为空，或者temperatures[i] <= 栈顶元素对应的温度，则将i入栈，temperatures[i]处理结束
     * 2. 不满足条件1，则将栈顶元素出栈，且我们知道temperatures[i]比栈顶元素对应的温度高，则元素i对应温度
     *    是第一个比栈顶元素对应温度高的，则栈顶元素后第(i-pop())天温度比其高。继续执行1，2步骤，直至满足步骤1
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures3(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        // 递减栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]){
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            // 这一步骤不能漏，说明stack为空，或者temperatures[i]满足递减栈要求了
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        每日温度_739 solution = new 每日温度_739();
        System.out.println(Arrays.toString(solution.dailyTemperatures1(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println(Arrays.toString(solution.dailyTemperatures2(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println(Arrays.toString(solution.dailyTemperatures3(new int[]{73,74,75,71,69,72,76,73})));
    }
}
