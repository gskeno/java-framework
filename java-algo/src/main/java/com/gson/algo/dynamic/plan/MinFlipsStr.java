package com.gson.algo.dynamic.plan;

/**
 * 翻转字符串
 * 原始字符串的每个字符都是"0"或者"1"，
 * 翻转后的字符串的"0"总在"1"的前面
 *
 * 例如输入字符串 "00110"，至少需要翻转一个字符才能满足要求，
 * 即将最后一个"0"翻转为"1"
 */
public class MinFlipsStr {

    /**
     * 重点是找到状态转移方程
     * 定义 f(i) 表示字符串(由s[0]到s[i]连续的共 i+1个字符组成)满足要求，且最后一个字符为"0"所需要的最小翻转次数
     *
     * 定义 g(i) 表示字符串(由s[0]到s[i]连续的共 i+1个字符组成)满足要求，且最后一个字符为"1"所需要的最小翻转次数
     *
     * 则存在
     *      f(i) = f(i-1),     当s[i] = 0 时,  不需要翻转s[i]
     *      f(i) = f(i-1) + 1, 当s[i] = 1 时,  需要翻转s[i]
     * -------------------------------------------------
     *      g(i) = min(f(i-1), g(i-1)) + 1, 当s[i] = 0 时, 需要翻转s[i]
     *      g(i) = min(f(i-1), g(i-1),      当s[i] = 1 时，不需要翻转s[i]
     *
     * @param S
     * @return
     */
    public int minFlipsStr(String S){
        int len = S.length();
        if (len == 0){
            return 0;
        }
        int[][] dp = new int[2][2];
        char ch = S.charAt(0);
        // dp[0] 模拟f函数
        // dp[1] 模拟g函数
        dp[0][0] = ch == '0' ? 0 : 1;
        dp[1][0] = ch == '1' ? 0 : 1;

        for (int i = 1; i < len; i++) {
            ch = S.charAt(i);
            int prev0 = dp[0][(i-1)%2];
            int prev1 = dp[1][(i-1)%2];

            dp[0][i%2] = prev0 + ( ch == '0' ? 0 : 1);
            dp[1][i%2] = Math.min(prev0, prev1) + (ch == '1' ? 0 : 1);
        }
        return Math.min(dp[0][(len-1)%2], dp[1][(len -1)%2]);
    }

    public static void main(String[] args) {
        MinFlipsStr minFlipsStr = new MinFlipsStr();
        String S = "00110";
        int i = minFlipsStr.minFlipsStr(S);
        System.out.println(i);
    }
}
