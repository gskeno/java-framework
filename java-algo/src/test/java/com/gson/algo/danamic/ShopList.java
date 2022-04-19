package com.gson.algo.danamic;

import java.util.Scanner;

/**
 https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4
 https://www.cxymm.net/article/qq_34594236/52432208
 */
public class ShopList {

    //50 5
    //20 3 5
    //20 3 5
    //10 3 0
    //10 2 0
    //10 1 0
    // bad case ，执行结果非预期
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] v = new int[n];
        int[] q = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < m; i++) {
            v[i] = sc.nextInt();
            value[i] = sc.nextInt() * v[i];
            q[i] = sc.nextInt();
        }

        System.out.println(f(v, value, q, m, n));
    }

    public static int f(int[] price, int[] value, int[] q, int n, int m) {

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //主件
                if (q[i - 1] == 0) {
                    if (price[i - 1] <= j) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - price[i - 1]] + value[i - 1]);
                    }

                }
                //附件
                else {
                    if (price[i - 1] + price[q[i - 1]] < j) { //判断时要加上主件
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - price[i - 1]] + value[i - 1]);
                    }
                }
            }
        }
        return dp[n][m];
    }
}
