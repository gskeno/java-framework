package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/number-of-substrings-with-only-1s/
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 *
 * 返回所有字符都为 1 的子字符串的数目。
 *
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 *
 */
public class 仅含1的子串数 {

    public int numSub(String s) {
        int startOf1 = -1;
        int endOf1 = -1;
        long ans = 0;
        int MOD = 1000000007;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1' && startOf1 == -1 && endOf1 == -1){
                startOf1 = i;
            }else if (s.charAt(i) == '0' && startOf1 != -1 && endOf1 == -1){
                endOf1 = i;
                String sub = s.substring(startOf1, endOf1);
                int L = sub.length();
                // 注意到可能溢出
                ans += (long)((double)(1+L)/2 * L);
                ans = ans % MOD;

                startOf1 = -1;
                endOf1 = -1;
            }
        }
        if (startOf1 != -1){
            endOf1 = s.length();
            String sub = s.substring(startOf1, endOf1);
            int L = sub.length();
            ans += (int)((double)(1+L)/2 * L);
            ans = ans % MOD;

        }
        return (int)ans;
    }

    public static void main(String[] args) {
        仅含1的子串数 solution = new 仅含1的子串数();
        int ans = 0;
        ans = solution.numSub("0110111");
        System.out.println(ans);

        ans = solution.numSub("101");
        System.out.println(ans);

        ans = solution.numSub("111111");
        System.out.println(ans);

        ans = solution.numSub("000");
        System.out.println(ans);

    }
}
