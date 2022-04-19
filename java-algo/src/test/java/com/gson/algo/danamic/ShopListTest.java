package com.gson.algo.danamic;

import java.util.Scanner;

/**
 *
 * https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4
 * https://mp.weixin.qq.com/s?__biz=Mzg5Mzc1NDI2NA==&mid=2247483693&idx=1&sn=32c82f7ea0340b5b178bf946b7db073e&chksm=c02b4fdaf75cc6cc14c5df2374f17e86c44d77e39c4d7bd7f402291899b81568be5af32d6ddb&token=666797164&lang=zh_CN#rd
 */
public class ShopListTest {
    private static int[][] dp;
    private static int[][] pr;
    //50 5
    //20 3 5
    //20 3 5
    //10 3 0
    //10 2 0
    //10 1 0

    //输入2
    //1000 5
    //800 2 0
    //400 5 1
    //300 5 1
    //400 3 0
    //500 2 0
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int m = sc.nextInt();
        int[] v = new int[m];
        int[] p = new int[m];
        int[] q = new int[m];
        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int p1 = sc.nextInt();
            int q1 = sc.nextInt();
            v[i] = v1;
            p[i] = p1;
            q[i] = q1;
        }
        packet(v, p, q, m, N);
        System.out.println(dp[m][N]);
    }

    /**
     *
     * @param v 价格
     * @param p 重要度
     * @param q 主副件
     * @param m  物品件数
     * @param money 用户能支出的钱
     */
    private static void packet(int[] v, int[] p, int[] q, int m, int money) {
        dp = new int[m + 1][money + 1];
        // pr用于记录物品的附件情况, 例如pr[5] = {1, 2} 代表第5号物品的附件分别为1号物品和2号物品
        pr = new int[m + 1][2];
        for (int i = 0; i < q.length; i++) {
            if (q[i] != 0) // q[i]代表第 i+1 号物品的主附标识
                // 走到这里说明是 附件
                if (pr[q[i]][0] == 0) // pr[X][0]默认值为0, 标识没附件, 若不为0, 说明已有一个附件
                    // 因为是附件, 所以q[i]的值为主件的索引, 注意看题干, 索引从1开始
                    // 所以  pr[q[i]][0] 代表第q[i]件物品的 第一个附件的 索引, 这个索引也是从1开始的,
                    // 为什么要加1 , 是为了和0区分, 因为 pr[q[i]][0]等于0 的话 代表没有附件,
                    // 所以此处索引从1 开始
                    pr[q[i]][0] = i + 1;
                else // 同理, pr[q[i]][1] 代表第q[i]件物品的 第二个附件的 索引,
                    pr[q[i]][1] = i + 1;
        }
        for (int i = 1; i <= m; i++) { // 先遍历 物品, 代表先锁定物品
            for (int j = 1; j <= money; j++) { // 后遍历 money, 也可以理解为money慢慢增大,
                // 0 0 0 0 0 0
                // 0 1 1
                // 0 2 2
                if (q[i - 1] != 0)
                    // 遍历到附件, 那此行的值, 直接copy上一行的值即可。
                    // 相当于附件根本没放入 对不对？,因为附件已经在它的主件 那一行考虑入内了,
                    // 所以 真实地， 遍历到每一件附件的时候, 都当成是不购买就行了, 不购买就是copy上一行的值。
                    dp[i][j] = dp[i - 1][j];
                else {// 这里一定是主件
                    if (v[i - 1] > j) // 判断主件是否可以购买
                        dp[i][j] = dp[i - 1][j]; // 不能购买，copy上一行的值
                    else {
                        // 能购买，按特征方程比较即可。
                        dp[i][j] = Math.max(dp[i - 1][j - v[i - 1]] + v[i - 1] * p[i - 1], dp[i - 1][j]);
                        // 能购买，或许此时 也能够 购买主件+第一件附件
                        int dp1 = 0;
                        if (pr[i][0] != 0) { // 主 + 附1
                            int fjId = pr[i][0] - 1;
                            int price = v[i - 1] + v[fjId];
                            if (price <= j) // 判断和j的大小，
                                // 运行到这里 说明确实 可以购买 主+ 第一件附件
                                dp1 = Math.max(dp[i - 1][j - price] + v[i - 1] * p[i - 1] + v[fjId] * p[fjId], dp[i - 1][j]);
                        }
                        // 由于 此时的 j 可以满足 购买主件 和  购买(主件+附1) 两种情况
                        // 所以 dp[i][j] 需要重新赋值， 新的值在 旧值和 dp1中产生，取最大
                        dp[i][j] = Math.max(dp[i][j], dp1);
                        // 能购买，或许此时 也能够 购买主见+第二件附件？
                        int dp2 = 0;
                        if (pr[i][1] != 0) { // 主 + 附2
                            int fjId = pr[i][1] - 1;
                            int price = v[i - 1] + v[fjId];
                            if (price <= j)// 判断和j的大小，
                                // 运行到这里 说明确实 可以购买 主+ 第二件附件
                                dp2 = Math.max(dp[i - 1][j - price] + v[i - 1] * p[i - 1] + v[fjId] * p[fjId], dp[i - 1][j]);
                        }
                        // dp[i][j] 需要重新赋值， 新的值在 旧值和 dp2中产生，取最大
                        dp[i][j] = Math.max(dp[i][j], dp2);
                        // 能购买主件，或许此时 也能够 购买主见+第一件附件+第二件附件？
                        int dp12 = 0;
                        if (pr[i][1] != 0) { // 主 + 附1 + 附2
                            int fjId1 = pr[i][0] - 1;
                            int fjId2 = pr[i][1] - 1;
                            int price = v[i - 1] + v[fjId2] + v[fjId1];
                            int vfjId1 = v[fjId1] * p[fjId1];
                            int vfjId2 = v[fjId2] * p[fjId2];
                            if (price <= j)
                                // 运行到这里 说明确实 可以购买 主+ 第一件附件 + 第二件附件
                                dp12 = Math.max(dp[i - 1][j - price] + v[i - 1] * p[i - 1] + vfjId1 + vfjId2, dp[i - 1][j]);
                        }
                        // dp[i][j] 需要重新赋值， 新的值在 旧值和 dp12中产生，取最大
                        dp[i][j] = Math.max(dp[i][j], dp12);
                    }
                }
            }
        }
    }
}
