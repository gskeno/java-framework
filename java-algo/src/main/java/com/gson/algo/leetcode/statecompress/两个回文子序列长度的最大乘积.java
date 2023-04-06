package com.gson.algo.leetcode.statecompress;

/**
 * https://leetcode.cn/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/
 */
public class 两个回文子序列长度的最大乘积 {
    /**
     * mask表示位选择的状态，第i个二进制为i，则代表s[i]被选择。
     * 如果mask(m)是回文，求mask(M^m) 子集合下的最长回文子序列
     *
     * @param s
     * @return
     */
    public int maxProduct(String s) {
        int n = s.length();
        int M = 1 << n;
        // dp[mask]表示mask选择状态下是否是回文
        boolean[] dp = new boolean[M];
        dp[0] = true;
        for (int mask = 1; mask < M; mask++) {
            if (Integer.bitCount(mask) == 1) {
                dp[mask] = true;
            }
            int highestOneBit = Integer.highestOneBit(mask);
            int lowestOneBit = Integer.lowestOneBit(mask);
            int newMask = ((mask ^ highestOneBit) ^ lowestOneBit);
            if (!dp[newMask]) {
                dp[mask] = false;
                continue;
            }
            int j = 0;
            while ((1 << j & lowestOneBit) == 0) {
                j++;
            }
            char left = s.charAt(j);
            int k = 0;
            while ((1 << k & highestOneBit) == 0) {
                k++;
            }
            char right = s.charAt(k);
            dp[mask] = left == right;
        }
        int ans = 0;
        for (int mask = 1; mask < M; mask++) {
            if (dp[mask]) {
                int remain = (M-1) ^ mask;
                for (int j = remain; j > 0; j = (j - 1) & remain) {
                    if (dp[j]){
                        ans = Math.max(ans, Integer.bitCount(mask) * Integer.bitCount(j));
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        两个回文子序列长度的最大乘积 solution = new 两个回文子序列长度的最大乘积();
        int ans = 0;
        ans = solution.maxProduct("leetcodecom");
        System.out.println(ans);

        System.out.println(0b101 ^ 0b001);
    }
}
