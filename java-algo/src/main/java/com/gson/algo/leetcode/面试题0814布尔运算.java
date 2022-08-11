package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/boolean-evaluation-lcci/
 * <p>
 * 输入: s = "1^0|0|1", result = 0
 * <p>
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 * <p>
 * 记忆化搜索
 */
public class 面试题0814布尔运算 {
    /**
     * dp[i][j][0]表示从s[i]到s[j]构成的子表达式运算结果为0的方案数量
     * dp[i][j][1]表示从s[i]到s[j]构成的子表达式运算结果为1的方案数量
     * <p>
     * 最终答案为dp[0][s.length-1][result]
     * <p>
     * 很容易能够看出来这是一个大规模转小规模的同类问题，一般用递归 + 记忆化搜索 (或者动态规划)可解决
     *
     * @param s
     * @param result
     * @return
     */
    public int countEval(String s, int result) {
        int n = s.length();
        int[][][] dp = new int[n][n][];
        dfs(dp, 0, n - 1, s);
        return dp[0][n - 1][result];
    }

    /**
     * ans[0]表示left到right子表达式运算结果为0的种类数量
     * ans[1]表示left到right子表达式运算结果为1的种类数量
     *
     * @param dp
     * @param left
     * @param right
     * @param s
     * @return
     */
    private int[] dfs(int[][][] dp, int left, int right, String s) {
        if (dp[left][right] == null) {
            int[] res = new int[2];
            // 表达式为一个数字
            if (left == right) {
                res[s.charAt(left) - '0'] = 1;
                dp[left][right] = res;
            }
            // 表达式长度为3，5，7，...
            else {
                // 以k作为分割符
                for (int k = left + 1; k < right; k += 2) {
                    char operator = s.charAt(k);
                    int[] leftRes = dfs(dp, left, k - 1, s);
                    int[] rightRes = dfs(dp, k + 1, right, s);
                    // 异或 , 两者不同运算结果为1，两者相同运算结果为0
                    if (operator == '^') {
                        res[0] += leftRes[0] * rightRes[0] + leftRes[1] * rightRes[1];
                        res[1] += leftRes[0] * rightRes[1] + leftRes[1] * rightRes[0];
                    } else if (operator == '&') {
                        res[0] += leftRes[0] * rightRes[0] + leftRes[1] * rightRes[0] + leftRes[0] * rightRes[1];
                        res[1] += leftRes[1] * rightRes[1];
                    } else {
                        res[0] += leftRes[0] * rightRes[0];
                        res[1] += leftRes[0] * rightRes[1] + leftRes[1] * rightRes[0] + leftRes[1] * rightRes[1];
                    }
                }
                // 别漏了
                dp[left][right] = res;
            }
        }
        return dp[left][right];
    }

    public static void main(String[] args) {
        面试题0814布尔运算 solution = new 面试题0814布尔运算();
        int kinds = solution.countEval("0&0&0&1^1|0", 1);
        System.out.println(kinds);
        kinds = solution.countEval("1^0|0|1", 0);
        System.out.println(kinds);

    }
}
