package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/Db3wC1/
 * @author ruchen
 * @date 2022/8/7
 */
public class 变换的迷宫_LCP31 {

    /**
     * 多纬矩阵
     * dp[t][i][j][u1][u2]表示使用t时刻，在i，j位置，使用了u1(0或1)张临时券，u2(0或1)张永久券
     * 的情况下，该状态是否可达(或者说该状态理论上是否可以存在)
     * @param maze
     * @return
     */
    public boolean escapeMaze(List<List<String>> maze) {
        int time = maze.size();
        int m = maze.get(0).size();
        int n = maze.get(0).get(0).length();

        boolean[][][][][] dp = new boolean[time][m][n][2][2];
        boolean[][][] visit = new boolean[m][n][2];
        // 0时刻,在原点，没使用任何券，状态成立
        dp[0][0][0][0][0] = true;
        for (int t = 1; t < time; t++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int u1 = 0; u1 < 2; u1++) {
                        for (int u2 = 0; u2 < 2; u2++) {
                            // 进行状态转移，上一刻本位置状态成立或者本位置上下左右任意一个位置状态成立
                            // 则本刻本位置状态才可能成立
                            // 共计5种状态转移
                            if (dp[t-1][i][j][u1][u2]
                                    || ((i-1) >=0 && dp[t-1][i-1][j][u1][u2])
                                    || ((i+1) < m && dp[t-1][i+1][j][u1][u2])
                                    || ((j-1) >=0 && dp[t-1][i][j-1][u1][u2])
                                    || ((j+1) < n && dp[t-1][i][j+1][u1][u2])
                            ){
                                // 当前位置是'.',无条件满足
                                if (maze.get(t).get(i).charAt(j) == '.'){
                                    dp[t][i][j][u1][u2] = true;
                                }
                                // 当前位置是'#',需要用券
                                else {
                                    // 如果临时券没用过，现在用掉，则可达当前位置
                                    // 疑问1: u1 == 0怎么能表示之前也没有用过临时券呢?
                                    // 回答1: 因为u1 == 0,能执行到这里，上面的5中dp状态转移条件必须满足一个，
                                    // 无论哪个满足，都表明在上一时刻，使用0张临时券，是可达上一 位置的。
                                    if (u1 == 0){
                                        dp[t][i][j][1][u2] = true;
                                    }

                                    // 如果永久券没用过，现在用掉
                                    if (u2 == 0){
                                        if (!visit[i][j][u1]){
                                            for (int k = t ; k < time; k++) {
                                                dp[k][i][j][u1][1] = true;
                                            }
                                            visit[i][j][u1] = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (dp[time-1][m-1][n-1][i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
            test1();
    }

    public static void test1(){
        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("...","...","..."));
        input.add(Arrays.asList(".##","###","##."));
        input.add(Arrays.asList(".##","###","##."));
        input.add(Arrays.asList(".##","###","##."));
        input.add(Arrays.asList(".##","###","##."));
        input.add(Arrays.asList(".##","###","##."));
        input.add(Arrays.asList(".##","###","##."));

        变换的迷宫_LCP31 solution = new 变换的迷宫_LCP31();
        boolean b = solution.escapeMaze(input);
        System.out.println(b);
    }


}
