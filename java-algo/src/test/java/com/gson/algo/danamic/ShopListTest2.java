package com.gson.algo.danamic;

import java.util.Scanner;

/**
 * https://blog.nowcoder.net/n/477ed49f893941bbb20ef8a0651acfd0
 *
 * 写得挺好的
 */
public class ShopListTest2 {
    /**
     * 输入用例1
     *     //50 5
     *     //20 3 5
     *     //20 3 5
     *     //10 3 0
     *     //10 2 0
     *     //10 1 0
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int money = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            money /= 10;
            // 3列，第1列是该件物品作为主件的价格，第2列是该件物品的附件1的价格，第3列是该件物品附件2的价格。
            // 如果该件物品是附件，则第1列，第2列，第3列都是0，
            // 如果该件物品是主件，第1列值非0，第2，3列是否非0，要看其有没有附件1和附件2。
            int[][] prices = new int[m+1][3];
            // 3列，第1列是该件物品作为主件的价值，第2列是该件物品的附件1的价值，第3列是该件物品附件2的价值。
            // 如果该件物品是附件，则第1列，第2列，第3列都是0，
            // 如果该件物品是主件，第1列值非0，第2，3列是否非0，要看其有没有附件1和附件2。
            int[][] weights = new int[m+1][3];
            for (int i = 1; i <= m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                a /= 10;//price
                b = b * a;//weight
                if (c == 0) {
                    // 主件
                    prices[i][0] = a;
                    weights[i][0] = b;
                } else if (prices[c][1] == 0) {
                    // 附件1
                    prices[c][1] = a;
                    weights[c][1] = b;
                } else {
                    // 附件2
                    prices[c][2] = a;
                    weights[c][2] = b;
                }
                sc.nextLine();
            }
            int[][] dp = new int[m+1][money+1];
            for (int i = 1; i <= m; i++) {
                for(int j = 1; j <= money; j++) {
                    int a = prices[i][0];
                    int b = weights[i][0];
                    int c = prices[i][1];
                    int d = weights[i][1];
                    int e = prices[i][2];
                    int f = weights[i][2];

                    // 表示该件物品是附件，进行跳过，即不选该件物品
                    if (a == 0){
                          dp[i][j] = dp[i-1][j];
                    }
                    // 从代码等价性来讲，是否使用if-else并没有区别，因为a=0时，b,c,d,e,f都是0
                    else {
                        dp[i][j] = j - a >= 0 ? Math.max(dp[i-1][j], dp[i-1][j-a] + b) : dp[i-1][j];
                        dp[i][j] = j-a-c >= 0 ? Math.max(dp[i][j], dp[i-1][j-a-c] + b + d):dp[i][j];
                        dp[i][j] = j-a-e >= 0 ? Math.max(dp[i][j], dp[i-1][j-a-e] + b + f):dp[i][j];
                        dp[i][j] = j-a-c-e >= 0 ? Math.max(dp[i][j], dp[i-1][j-a-c-e] + b +d + f):dp[i][j];
                    }
                }
            }

            for (int i = 0; i <=m; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= money; j++) {
                    sb.append(dp[i][j] * 10 + ",");
                }
            }

            System.out.println(dp[m][money] * 10);
        }
    }
}
