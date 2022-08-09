package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/Db3wC1/
 * @author ruchen
 * @date 2022/8/7
 */
public class 变换的迷宫_LCP31_状态机_贪心 {

    int[][][] dp;
    int[][] preUse;
    int m,n,t;

    public boolean escapeMaze(List<List<String>> maze) {
        m = maze.get(0).size();
        n = maze.get(0).get(0).length();
        t = maze.size();
        dp = new int[t][m][n];
        preUse = new int[m][n];

        //其他位置转移到此 状态转移表
        int[] next = {0,0,0,1,2,4};
        //上次在当前位置使用卷轴 状态转移表
        int[] preS = {0,0,2,3,3,4};

        for(int k = 0; k<t ;k++) {
            //初始化起点状态
            dp[k][0][0]=5;
        }
        for (int ct = 1; ct < t; ct++) {
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(i==0 && j==0) continue;
                    int pre = calMax(ct-1, i, j);
                    if (maze.get(ct).get(i).charAt(j) != '.') {
                        dp[ct][i][j] = Math.max(next[pre], preS[preUse[i][j]]);
                        if (i == 0 && j == 9){
                            //System.out.println(ct + "," + i + "," + j + "=" + dp[ct][i][j]);
                        }
                        preUse[i][j] = dp[ct][i][j];
                        System.out.println("preUse[" + i + "][" + j +"]=" + preUse[i][j] + ",time=" + ct);
                    } else {
                        dp[ct][i][j] = pre;
                        if (i == 0 && j == 9){
                            //System.out.println(ct + "," + i + "," + j + "=" + dp[ct][i][j]);
                        }
                    }
                }
            }
            if(dp[ct][m-1][n-1] > 0){
                System.out.println("dp[" + ct + "][" + (m-1) + "][" + (n-1) + "]=" + dp[ct][m-1][n-1]);
                return true;
            }
        }

        return false;
    }

    int calMax(int t,int i, int j) {
        int max = dp[t][i][j];
        if (i>0) max = Math.max(max, dp[t][i-1][j]);
        if (j>0) max = Math.max(max, dp[t][i][j-1]);
        if (i<m-1) max = Math.max(max, dp[t][i+1][j]);
        if (j<n-1) max = Math.max(max, dp[t][i][j+1]);
        return max;
    }

    public static void main(String[] args) {
            test2();
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

        变换的迷宫_LCP31_状态机_贪心 solution = new 变换的迷宫_LCP31_状态机_贪心();
        boolean b = solution.escapeMaze(input);
        System.out.println(b);
    }

    public static void test2(){
        List<List<String>> input = new ArrayList<>();

        input.add(Arrays.asList("..###..##."));
        input.add(Arrays.asList("..######.."));

        input.add(Arrays.asList(".#.#..#.#."));
        input.add(Arrays.asList(".##..#.##."));

        input.add(Arrays.asList(".########."));
        input.add(Arrays.asList(".#..##...."));

        input.add(Arrays.asList(".#.#####.."));
        input.add(Arrays.asList(".###.###.."));

        input.add(Arrays.asList(".########."));
        input.add(Arrays.asList(".##.##.##."));

        input.add(Arrays.asList(".###...##."));
        input.add(Arrays.asList(".#####.#.."));

        input.add(Arrays.asList(".##..###.."));
        input.add(Arrays.asList("..#####.#."));

        input.add(Arrays.asList(".####.###."));
        input.add(Arrays.asList(".###.###.."));
        input.add(Arrays.asList("..######.."));



        变换的迷宫_LCP31_状态机_贪心 solution = new 变换的迷宫_LCP31_状态机_贪心();
        boolean b = solution.escapeMaze(input);
        System.out.println(b);
    }


}
