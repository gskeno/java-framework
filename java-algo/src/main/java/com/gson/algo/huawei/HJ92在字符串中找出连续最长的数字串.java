package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/2c81f88ecd5a4cc395b5308a99afbbec
 * a8a72a6a5yy98y65ee1r2
 */
public class HJ92在字符串中找出连续最长的数字串 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String result = getStrAndMaxLen(line);
            System.out.println(result);
        }
    }

    public static String getStrAndMaxLen(String line) {
        int maxLength = 0;
        StringBuilder sb = new StringBuilder();

        int len = 0;
        StringBuilder tempSb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c - '0' >= 0 && '9' - c >= 0) {
                len++;
                tempSb.append(c);
            }
            // 遇到一个数字串后的第一个非数字字符,且该数字串长于当前已遍历的最长数字串
            else if (len > 0 && len > maxLength) {
                sb.delete(0, sb.length());
                maxLength = len;

                sb.append(tempSb);
                tempSb.delete(0, tempSb.length());
                len = 0;

            }
            // 遇到一个数字串后的第一个非数字字符,且该数字串等于当前已遍历的最长数字串
            else if (len > 0 && len == maxLength) {
                sb.append(tempSb);
                tempSb.delete(0, tempSb.length());
                len = 0;
            }
            // 遇到一个数字串后的第一个非数字字符,且该数字串短于当前已遍历的最长数字串
            else if (len > 0 && len < maxLength) {
                tempSb.delete(0, tempSb.length());
                len = 0;
            }
        }

        if (len > 0 && len > maxLength) {
            sb.delete(0, sb.length());
            maxLength = len;
        }

        if (len > 0 && len == maxLength) {
            sb.append(tempSb);
        }

        return sb.append(",").append(maxLength).toString();
    }
}
