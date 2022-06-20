package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/regular-expression-matching/
 *
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * s,p的长度都大于0
 *
 *
 * https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
 * 很不错的解释
 *
 */
public class 正则表达式匹配_10 {

    public boolean isMatch(String s, String p) {
        return isMatch1(s, p);
    }
    /**
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     * 3, If p.charAt(j) == '*':
     *    here are two sub conditions:
     *                1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
     *                2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
     *                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     *                            or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
     *                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p){
        // dp[i][j]表示s的前i个字符与p的前j个字符是否匹配。
        // i最大合法值是s.length;j最大合法值是p.length
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 由于s,j长度都>0,所以dp[0][0]表示s的前0个字符与j的前0个字符是否匹配没有现实意义。
        // 但是循环中有用到该值，且空字符串与空模式串自然是完全匹配的
        dp[0][0] = true;
        for (int i = 1; i <=s.length() ; i++) {
            for (int j = 1; j <=p.length() ; j++) {
                if (p.charAt(j-1) == s.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1) == '*'){
                    if (p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.'){
                        dp[i][j] = dp[i][j-2];
                    }else {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1] || dp[i][j-2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
