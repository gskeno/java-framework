package com.gson.algo.leetcode.monotonicstack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/steps-to-make-array-non-decreasing/
 *
 * 输入：nums = [5,3,4,4,7,3,6,11,8,5,11]
 * 输出：3
 * 解释：执行下述几个步骤：
 * - 步骤 1 ：[5,3,4,4,7,3,6,11,8,5,11] 变为 [5,4,4,7,6,11,11]
 * - 步骤 2 ：[5,4,4,7,6,11,11] 变为 [5,4,7,11,11]
 * - 步骤 3 ：[5,4,7,11,11] 变为 [5,7,11,11]
 *   [5,7,11,11] 是一个非递减数组，因此，返回 3 。
 *
 */
public class 使数组按照非递减顺序排列 {

    /**
     * 提示1: 只要一个元素左侧有大于其的元素，则该元素需要被移除，否则，可以保留
     * 提示2: 设置一个数组dp[], dp[i]表示该元素应该在第几轮才被删除。初始值为0,
     * 提示3: 维护一个单调递减栈，当nums[i] >= 栈顶元素时，不断移除栈顶元素, dp[i] = max{dp[被移除的栈顶元素索引}
     * @param nums
     * @return
     */
    public int totalSteps(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        // 每个元素被删除的轮次，初始值为0，不被删除
        int[] dp = new int[nums.length];
        int ans = 0;
        // 单调递减栈
        for (int i = 0; i < nums.length; i++) {
            // 被弹出的元素 都比 当前元素小，这些元素被删除轮次的最大值赋值到dp[i]上
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]){
                Integer top = stack.pop();
                dp[i] = Math.max(dp[i], dp[top]);
            }
            // 栈非空，则当前元素也要被删除，故dp[i]+=1
            if (!stack.isEmpty()){
                 dp[i] += 1;
            }
            // 栈为空，则当前元素是历史最大值，不需要删除。
            else {
                dp[i] = 0;
            }
            stack.push(i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        使数组按照非递减顺序排列 solution = new 使数组按照非递减顺序排列();
        int ans = 0;
        ans = solution.totalSteps(new int[]{5,3,4,4,7,3,6,11,8,5,11});
        System.out.println(ans);
    }
}
