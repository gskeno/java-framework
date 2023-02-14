package com.gson.algo.leetcode.monotonicstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/
 */
public class 叶值为1的最小代价生成树 {
    /**
     * 贪心: 每次将数组中乘积最小的两个元素的乘积值加入到答案中去，并将这两个元素的较小值从数组中移除出去。
     * 重复该操作，直至数组中只剩下一个元素
     *
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        int sum = 0;
        while (list.size() > 1) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int multi = list.get(i) * list.get(i + 1);
                if (multi <= min) {
                    min = multi;
                    minIdx = list.get(i) <= list.get(i + 1) ? i : i + 1;
                }
            }
            sum += min;
            list.remove(minIdx);
        }
        return sum;
    }

    /**
     * 动态规划
     * dp[i,j]表示i~j区间叶值最小代价生成树，则肯定存在一个k, i <= k <j
     * i~k 区间是树根的左子树，k+1~j是树根的右子树，则存在关系
     * dp[i,j] = dp[i,k] + dp[k+1,j] + max(array[i~k]) * max(array[k+1~j]),
     * <p>
     * dp[0,3] -> dp[0,0] + dp[1,3] + max(array[0~0]) * max(array[1,3]); k= 0
     * dp[0,3] -> dp[0,1] + dp[2,3] + max(array[0~1]) * max(array[2,3]); k= 1
     * dp[0,3] -> dp[0,2] + dp[3,3] + max(array[0~2]) * max(array[3,3]); k= 2
     * <p>
     * 要求出区间宽度为3的dp，要先求出区间宽度为0，1，2的dp。
     * <p>
     * dp[i][i] = 0
     *
     * @param arr
     * @return
     */
    public int mctFromLeafValues1(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        // 处理长度为width的区间
        for (int width = 1; width < arr.length; width++) {
            // 区间起始点
            for (int start = 0; start < arr.length - width; start++) {
                dp[start][start + width] = Integer.MAX_VALUE;
                // 区间内的分断点
                for (int middle = start; middle < start + width; middle++) {
                    int max1 = Integer.MIN_VALUE;
                    for (int i = start; i <= middle; i++) {
                        max1 = Math.max(max1, arr[i]);
                    }
                    int max2 = Integer.MIN_VALUE;
                    for (int i = middle + 1; i <= start + width; i++) {
                        max2 = Math.max(max2, arr[i]);
                    }
                    dp[start][start + width] = Math.min(dp[start][start + width] , dp[start][middle] + dp[middle + 1][start + width] + max1 * max2);
                }
            }
        }
        return dp[0][arr.length - 1];
    }

    /**
     * 单调栈
     * @param arr
     * @return
     */
    public int mctFromLeafValues2(int[] arr) {
        int ans = 0;
        // 维护一个单调递减栈
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int i = 0; i < arr.length; i++) {
            while (stack.peek() <= arr[i] ){
                Integer smallest = stack.pop();
                ans += smallest * (Math.min(stack.peek(), arr[i]));
            }
            stack.push(arr[i]);
        }
        while (stack.size() > 2){
            ans += stack.pop() * stack.peek();
        }
        return ans;
    }

    public static void main(String[] args) {
        叶值为1的最小代价生成树 solution = new 叶值为1的最小代价生成树();
        int ans;
        ans = solution.mctFromLeafValues(new int[]{6, 2, 4});
        System.out.println(ans);

        ans = solution.mctFromLeafValues1(new int[]{6, 2, 4});
        System.out.println(ans);

        ans = solution.mctFromLeafValues2(new int[]{6, 2, 4});
        System.out.println(ans);
    }
}
