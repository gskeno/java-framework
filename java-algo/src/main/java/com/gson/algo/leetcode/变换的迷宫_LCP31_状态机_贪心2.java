package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/Db3wC1/
 * @author ruchen
 * @date 2022/8/7
 */
public class 变换的迷宫_LCP31_状态机_贪心2 {

    int[][][] dp;
    int[][] sp;
    int[][] preUse;
    int m,n,t;

    public boolean escapeMaze(List<List<String>> maze) {
        m = maze.get(0).size();
        n = maze.get(0).get(0).length();
        t = maze.size();
        dp = new int[t][m][n];
        // 坐标位置的下限状态，状态值越低，说明当前拥有券状态越糟糕
        // 下限，表示以后该位置不管是陷阱还是过道，状态不会比下限状态更糟糕(即状态值更小)
        sp = new int[m][n];


        // 状态值    0         1         2         3        4
        // 含义     不可达  无任何券    有临时券   有永久券   有两张券

        // 状态机模拟优先使用临时券后的状态
        // 比如machine[4]=3,可以理解为我当前有两张券，但是我走到了一个陷阱位置，所以我要使用一张券,
        // 这里我们模拟优先使用临时券，所以使用完后，状态变为3(只剩下一张永久券)
        // 其他情况类似分析
        int[] machine = new int[]{0, 0, 1, 1, 3};

        dp[0][0][0] = 4;

        for (int ct = 1; ct < t; ct++) {
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    int status = calMax(ct, i, j);
                    if (maze.get(ct).get(i).charAt(j) != '.') {
                        // 拥有永久券时，去设置在该位置的状态下限
                        // 在该位置如果使用掉永久券，可以得到该位置状态下限，
                        // 状态4的下限为2，状态3的下限为1
                        if (status>=3){
                            sp[i][j] = Math.max(sp[i][j], status - 2);
                        }
                        // machine, 4->3; 3->1; 2->1; 1->0; 0->0
                        dp[ct][i][j] = Math.max(sp[i][j], machine[status]);
                    } else {
                        dp[ct][i][j] = status;
                    }
                }
            }
            // 任一有效时刻，达到终点，且状态有效，则OK
            if(dp[ct][m-1][n-1] > 0){
                System.out.println("有效值" + ct );
                return true;
            }
        }

        return false;
    }

    int calMax(int now,int i, int j) {
        int pre = now - 1;
        int max = dp[pre][i][j];
        if (i>0) {
            max = Math.max(max, dp[pre][i-1][j]);
        }
        if (j>0) {
            max = Math.max(max, dp[pre][i][j-1]);
        }
        if (i<m-1) {
            max = Math.max(max, dp[pre][i+1][j]);
        }
        if (j<n-1) {
            max = Math.max(max, dp[pre][i][j+1]);
        }
        return max;
    }

    public static void main(String[] args) {
            test3();
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

        变换的迷宫_LCP31_状态机_贪心2 solution = new 变换的迷宫_LCP31_状态机_贪心2();
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



        变换的迷宫_LCP31_状态机_贪心2 solution = new 变换的迷宫_LCP31_状态机_贪心2();
        boolean b = solution.escapeMaze(input);
        System.out.println(b);
    }

    public static void test3(){
        List<List<String>> input = new ArrayList<>();

        input.add(Arrays.asList(".##",
                                "###",
                                "###")
        );

        input.add(Arrays.asList(".###",
                ".###",
                "###.")
        );
        input.add(Arrays.asList(".###",
                "####",
                ".##.")
        );
        input.add(Arrays.asList(".###",
                ".###",
                "###.")
        );
        input.add(Arrays.asList(".###",
                "####",
                "###.")
        );
        input.add(Arrays.asList(".###",
                "##.#",
                "###.")
        );
        input.add(Arrays.asList(".###",
                "###.",
                "###.")
        );
        input.add(Arrays.asList(".###",
                "####",
                "###.")
        );

        变换的迷宫_LCP31_状态机_贪心2 solution = new 变换的迷宫_LCP31_状态机_贪心2();
        boolean b = solution.escapeMaze(input);
        System.out.println(b);
    }
}