package com.gson.algo.leetcode;

import java.util.Scanner;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * 单词A word1 = "horse", 单词B word2 = "ros"
 *     从horse 转变为 ros，可以认为是由下面三种方式任意之一转变而来
 *     horse已经转化为ro的基础上，单词A末尾添加s。则A转化为了B
 *     hors已经转化为ros的基础上，单词B末尾添加e。则A转化为了B
 *     hors已经转化为ro的基础上， 单词A末尾的单词e变为单词B末尾的单词s。则A转化为了B。
 */
public class 编辑距离72_动态规划 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            int distance = getDistance(str1, str2);
            System.out.println(distance);
        }
    }

    /**
     * 动态规划
     * dp[i][j]表示str1的前i个字符转变为str2的前j个字符所需要的最少变换步骤(插入、删除、替换字符)
     * @param str1
     * @param str2
     * @return
     */
    public static int getDistance(String str1, String str2){
        // 求dp[str1.length][str2.length]，即为答案
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            // 比如str1的前5个字符组成的字符串转化为str2的前0个字符组成的字符串(空字符串),需要至少5个变换步骤
            dp[i][0] = i;
        }

        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }

        // 处理str1的前i个字符组成的字符串与 str2的前j个字符组成的字符串
        for (int i = 1; i <= str1.length() ; i++) {
            for (int j = 1; j <= str2.length(); j++) {
                dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
                // str1的第i个字符替换为str2的第j个字符
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + 1);
                // str1的第i个字符串等于str2的第j个字符串
                if (str1.charAt(i-1) == str2.charAt(j -1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                //System.out.println("dp[" + i + "," + j + "]=" + dp[i][j]);
            }
        }
        return dp[str1.length()][str2.length()];
    }

}
