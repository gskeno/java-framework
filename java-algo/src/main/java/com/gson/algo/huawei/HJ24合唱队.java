package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * 动态规划
 */
public class HJ24合唱队 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String total = scanner.nextLine();
            String heightStr = scanner.nextLine().trim();
            String[] heights = heightStr.split(" ");

            System.out.println(getEvictNums(heights));
        }
    }

    /**
     * dp[i]表示第i个人左边出现最多的满足要求的人数
     * f[i] 表示第i个人右边出现最多的满足要求的人数
     *
     * @param heights
     * @return
     */
    public static int getEvictNums(String[] heights) {
        // 186 186 150 200 160 130 197 200
        int[] dp = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < i; j++) {
                // i号人如果比它之前的j号人的更高，则i号人左边出现最多的人数，则是j号人左边出现的最多人数 + 1。
                // 注意到j有很多个，在i前面的都是j，所以要把j的范围遍历完，取得最多的人数再+1
                if (Integer.valueOf(heights[i]) > Integer.valueOf(heights[j]) && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // 186 186 150 200 160 130 197 200
        int[] f = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            for (int j = heights.length - 1; j > i; j--) {
                if (Integer.valueOf(heights[i]) > Integer.valueOf(heights[j]) && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                }
            }
        }

        int maxLen = 0;

        for (int i = 0; i < heights.length; i++) {
            // 以i号人作为中间人时，队列需要的人数
            int personsInQueue = dp[i] + f[i] + 1;
            maxLen = Math.max(maxLen, personsInQueue);
        }

        return heights.length - maxLen;
    }
}
