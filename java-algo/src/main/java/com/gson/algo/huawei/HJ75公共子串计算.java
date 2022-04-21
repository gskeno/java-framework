package com.gson.algo.huawei;

import java.util.Scanner;

public class HJ75公共子串计算 {
    /**
     * 字符串s1, s2。
     * 定义dp[i][j] 为以s1[i]和s2[j]为结尾的两个公共子串的长度。(即s1[i]=s2[j])
     *
     * 则存在以下关系
     * dp[i][j] = dp[i-1][j-1] + 1; 当 s1[i]=s2[j]时
     *
     * dp[i][j] = 0; 当 s1[i] != s2[j]时
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            System.out.println(getCommonLen(s1, s2));
        }
    }

    public static int getCommonLen(String s1, String s2){
        int[][] dp = new int[s1.length()][s2.length()];
        int maxLen = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)){
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    if (dp[i][j] > maxLen){
                        maxLen = dp[i][j];
                    }
                }else {
                    // 该步骤可以省略
                    dp[i][j] = 0;
                }
            }
        }
        return maxLen;
    }
}
