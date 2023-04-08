package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HJ16购物单 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int money = in.nextInt();
            int goodsNum = in.nextInt();
            // 第0行无意义
            int[][] goods = new int[goodsNum + 1][3];
            // 主附件关系
            List<List<Integer>> mainSecondRelation = new ArrayList<>();
            for (int i = 0; i < goodsNum + 1; i++) {
                mainSecondRelation.add(new ArrayList<>());
            }
            // 主件个数
            List<Integer> mainGoodList = new ArrayList<>();
            for (int i = 1; i <= goodsNum; i++) {
                // 价格
                goods[i][0] = in.nextInt();
                // 重要度
                goods[i][1] = in.nextInt();
                // 归属主件编号，值为0时，本身就是主件
                goods[i][2] = in.nextInt();
                // 一主件对应多附件
                if (goods[i][2] != 0) {
                    mainSecondRelation.get(goods[i][2]).add(i);
                } else {
                    mainGoodList.add(i);
                }
            }
            int[][] dp = new int[goodsNum + 1][money + 1];
            for (int i = 1; i <= goodsNum; i++) {
                for (int j = 1; j <= money; j++) {
                    // 跳过附件
                    if (goods[i][2] != 0){
                        dp[i][j] = dp[i - 1][j];
                        continue;
                    }
                    // 第i个主件可以选或者不选
                    // case1 不选第i个主件
                    dp[i][j] = dp[i - 1][j];
                    // case2 选第i个主件，但附件不选
                    if (j - goods[i][0] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i][0]] + goods[i][0] * goods[i][1]);
                    }
                    List<Integer> secondList = mainSecondRelation.get(i);
                    // 附件1，和附件2的编号
                    int second1 = secondList.size() > 0 ? secondList.get(0) : 0;
                    int second2 = secondList.size() > 1 ? secondList.get(1) : 0;
                    // case3 选第i个主件和附件1
                    if (second1 != 0 && j - goods[i][0] - goods[second1][0] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i][0] - goods[second1][0]]
                                + goods[i][0] * goods[i][1]
                                + goods[second1][0] * goods[second1][1]);
                    }

                    // case4 选第i个主件和附件2
                    if (second2 != 0 && j - goods[i][0] - goods[second2][0] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i][0] - goods[second2][0]]
                                + goods[i][0] * goods[i][1]
                                + goods[second2][0] * goods[second2][1]);
                    }

                    // case4 选第i个主件和附件，2
                    if (second1 != 0 && second2 != 0 && j - goods[i][0] - goods[second1][0] - goods[second2][0] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i][0] - goods[second1][0] - goods[second2][0]]
                                + goods[i][0] * goods[i][1]
                                + goods[second1][0] * goods[second1][1]
                                + goods[second2][0] * goods[second2][1]);
                    }
                }
            }
            System.out.println(dp[goodsNum][money]);
        }
    }
}