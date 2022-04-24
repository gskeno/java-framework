package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/6d9d69e3898f45169a441632b325c7b4
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

    public static int getEvictNums2(int n, int[] heights){
        int[] seq = new int[n], rev_seq = new int[n];
        int[] k = new int[n];  // 用于记录以i为终点的从左向右和从右向走的子序列元素个数
        seq[0] = heights[0];  // 初始化从左向右子序列首元素为第一个元素
        int index = 1; // 记录当前子序列的长度
        for (int i = 1; i < n; i++) {
            if (heights[i] > seq[index - 1]) {  // 当当前元素大于递增序列最后一个元素时
                k[i] = index;  // 其左边元素个数
                seq[index++] = heights[i];  // 更新递增序列
            } else {  // 当当前元素位于目前维护递增序列之间时
                // 使用二分搜索找到其所属位置
                int l = 0, r = index - 1;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (seq[mid] < heights[i]) l = mid + 1;
                    else r = mid;
                }
                seq[l] = heights[i];  // 将所属位置值进行替换
                k[i] = l;  // 其左边元素个数
            }
        }

        // 随后，再从右向左进行上述操作
        rev_seq[0] = heights[n - 1];
        index = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (heights[i] > rev_seq[index - 1]) {
                k[i] += index;
                rev_seq[index++] = heights[i];
            } else {
                int l = 0, r = index - 1;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (rev_seq[mid] < heights[i]) l = mid + 1;
                    else r = mid;
                }
                rev_seq[l] = heights[i];
                k[i] += l;
            }
        }

        int max = 1;
        for (int num : k)
            if (max < num) max = num;
        // max+1为最大的k
        return n - max - 1;

    }
}
