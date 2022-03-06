package com.gson.algo.dynamic.plan.pairsequence;

/**
 * 子序列数目
 * 输入字符串S和T，请计算字符串S中有多少个子序列等于字符串T。
 * 比如，在字符串"appplep"中，有三个子序列等于字符串"apple"
 * [app] p [le] p
 * [ap] p [ple] p
 * [a] p [pple] p
 */
public class SubSequenceCount {
    /**
     * 状态转移方程
     * f(i,j)表示字符串S下标从0到i的子字符串(记为S[0...i])中等于字符串T下标从0到j的子字符串
     * (记为T[0..j]) 的子序列的数目。
     *
     * 如果字符串S的长度为m，字符串T的长度为n。那么f(m-1, n-1) 就是字符串S中等于字符串T的子序列的数目。
     *
     * 存在以下关系
     *
     * 1. f(i,j) = 0, 当 i < j时；
     *
     * 2. f(i,j) = f(i-1, j-1) + f(i-1,j), 当S[i]=T[j]时;
     *
     * 3. f(i,j) = f(i-1, j), 当S[i] 不等于 T[j]时；
     *
     * 4. f(-1,-1) = 1, 此时S,T都是空字符串
     *
     * 5. f(-1, j) = 0, 当j >=0时
     *
     * 6. f(i, -1) = 1, 当i>=0时，表示S的空子序列正好满足T为空
     */


    public int numDistinct(String S, String T){
        // f(i,j)的值保存在dp[i+1,j+1]中
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        // 表示f(-1,-1) = 1
        dp[0][0] = 1;

        for (int i = 0; i < S.length(); i++) {
            // 表示f(i,-1) = 1
            dp[i+1][0] = 1;
            for (int j = 0; j <= i && j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + dp[i][j+1];
                }else {
                    dp[i+1][j+1] = dp[i][j+1];
                }
            }
        }

        return dp[S.length()][T.length()];
    }

    public static void main(String[] args) {
        SubSequenceCount subSequenceCount = new SubSequenceCount();
        String S = "appplep";
        String T = "apple";
        int count = subSequenceCount.numDistinct(S, T);
        System.out.println(count);
    }
}
