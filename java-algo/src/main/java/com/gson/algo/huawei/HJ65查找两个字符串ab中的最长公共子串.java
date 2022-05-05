package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * abcdefghijklmnop
 *    abcsafjklmnopqrstuvw
 *
 * 最长公共子串为 jklmnop
 */
public class HJ65查找两个字符串ab中的最长公共子串 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            System.out.println(getMaxLenSub(s1.length() <= s2.length() ? s1 : s2,
                    s1.length() <= s2.length() ? s2 : s1));
        }
    }

    /**
     * s1的长度小于等于s2的长度
     * 字符串 s1, s2。定义dp[i][j]表示以s1[i]和s2[j]结尾的公共子串的最大长度。
     *
     * 当 s1[i] = s2[j]时，dp[i][j] = dp[i-1][j-1] + 1
     * 当 s1[i] != s2[j]时， dp[i][j] = 0。因为以s1[i]和s2[j]结尾，没有公共子串
     * @param s1
     * @param s2
     * @return
     */
    public static String getMaxLenSub(String s1, String s2){
        int[][] dp = new int[s1.length()][s2.length()];
        // 最长公共子串的长度
        int maxLen = 0;
        // 最长公共子串对应的s1的的位置
        int indexOfS1 = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)){
                    // s1的首字符与s2中的某个字符相同，或者s2的首字符与s1中的某个字符相等
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    // 标记最大长度
                    if (dp[i][j] > maxLen){
                        maxLen = dp[i][j];
                        indexOfS1 = i;
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return s1.substring(indexOfS1 - maxLen + 1, indexOfS1+1);
    }
}
