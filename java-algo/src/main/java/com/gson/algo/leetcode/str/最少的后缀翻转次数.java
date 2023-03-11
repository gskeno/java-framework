package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/minimum-suffix-flips/
 */
public class 最少的后缀翻转次数 {

    /**
     * 10111 <--- 10000 <--- 11 111 <--- 00000
     * @param target
     * @return
     */
    public int minFlips(String target) {
        int n = target.length();
        int i = 0;
        while (i < n && target.charAt(i) != '1'){
            i++;
        }
        // i为字符串的第一个"1"所在的下标

        if (i == n){
            return 0;
        }
        int ans = 1;
        i++;
        while (i < n){
            if (target.charAt(i) != target.charAt(i-1)){
                ans++;
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        最少的后缀翻转次数 solution = new 最少的后缀翻转次数();
        int ans;
        ans = solution.minFlips("10111");
        System.out.println(ans);

        ans = solution.minFlips("101");
        System.out.println(ans);

        ans = solution.minFlips("00000");
        System.out.println(ans);
    }
}
