package com.gson.algo.huawei;

import java.util.Scanner;

public class HJ32密码截取 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
        }
    }

    /**
     * 获取回文串的最大的长度
     * @return
     */
    public static int getPalindromicMaxLength(String s){
        // dp[i][j]表示索引i到索引j的子串是否是回文。
        boolean dp[][] = new boolean[s.length()][s.length()];
        // j>=i
        int maxLen = 1;
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j){
                    dp[i][j] = true;
                }else if (j - i == 1){
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }else {
                    // 这里可以看到 dp[i][j]依赖 dp[i+1][j-1]，所以后者要先计算
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i+1][j-1];
                }

                if (dp[i][j]){
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
}
