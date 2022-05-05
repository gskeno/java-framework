package com.gson.algo.huawei;

import java.util.Scanner;

/**
 *
 */
public class HJ63DNA序列 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.next();
            int n = scanner.nextInt();
            System.out.println(getSubStr(line, n));
        }
    }

    /**
     * 找出G，C比例最高的长度为n的子串
     *
     * @param line
     * @param n
     * @return
     */
    public static String getSubStr(String line, int n) {
        int lastIndex = line.length() - n;

        int leftPos = 0;
        int maxGcNums = 0;
        int gcNums = 0;
        for (int i = 0; i < n; i++) {
            if (line.charAt(i) == 'G' || line.charAt(i) == 'C') {
                gcNums++;
            }
            ;
        }
        maxGcNums = gcNums;
        leftPos = 0;

        for (int i = 1; i <= lastIndex; i++) {
            if (line.charAt(i - 1) == 'G' || line.charAt(i - 1) == 'C') {
                gcNums--;
            }
            if (line.charAt(i + n - 1) == 'G' || line.charAt(i + n - 1) == 'C') {
                gcNums++;
            }

            if (gcNums > maxGcNums) {
                maxGcNums = gcNums;
                leftPos = i;
            }
        }

        return line.substring(leftPos, leftPos + n);
    }

    /**
     * 双指针
     * i 记录子串的初始位置
     * j 记录子串的终止位置 则存在 j - i + 1 = n
     * gcNums表示遍历时子串中G,C的数量
     * maxGcNums表示拥有GC最多的子串包含的G,C的书香
     * leftPos 表示拥有最多GC的子串的第一个字符的位置
     *
     * @param line
     * @param n
     * @return 可能会超时，其实我们分析在窗口滑动的过程中，滑动到什么位置时，GC
     * 的个数最多即可，不必知道其个数具体是多少，这样就少了第一个子串构造
     * 时查询出GC个数是多少的过程
     */
    public static String getSubStr2(String line, int n) {
        int i = 0;
        int j = i + (n - 1);
        int gcNums = 0;
        int maxGcNums = 0;
        int leftPos = 0;
        for (int k = 0; k <= j; k++) {
            if (line.charAt(k) == 'G' || line.charAt(k) == 'C') {
                gcNums++;
            }
        }
        maxGcNums = gcNums;
        for (i = 1, j = n; j < line.length(); i++, j++) {
            if (line.charAt(i - 1) == 'C' || line.charAt(i - 1) == 'G') {
                gcNums--;
            }
            if (line.charAt(j) == 'C' || line.charAt(j) == 'G') {
                gcNums++;
            }
            if (gcNums > maxGcNums) {
                maxGcNums = gcNums;
                leftPos = i;
            }
        }
        return line.substring(leftPos, leftPos + n);
    }

    public static String getSubStr3(String line, int n) {
        int gcNums = 0;
        int maxGcNums = gcNums;
        int leftPos = 0;
        for (int i = 1, j = n; j < line.length(); i++, j++) {
            if (line.charAt(i - 1) == 'C' || line.charAt(i - 1) == 'G') {
                gcNums--;
            }
            if (line.charAt(j) == 'C' || line.charAt(j) == 'G') {
                gcNums++;
            }
            if (gcNums > maxGcNums) {
                maxGcNums = gcNums;
                leftPos = i;
            }
        }

        // 我们只需要知道leftPos而已
        return line.substring(leftPos, leftPos + n);
    }
}
