package com.gson.algo.leetcode.graph;

/**
 * https://leetcode.cn/problems/find-the-town-judge/
 */
public class 找到小镇的法官_997 {

    public int findJudge(int n, int[][] trust) {
        // 入边
        int[] inList = new int[n + 1];
        // 出边
        int[] outList = new int[n + 1];

        for (int[] edge : trust) {
            outList[edge[0]]++;
            inList[edge[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inList[i] == n - 1 && outList[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        找到小镇的法官_997 solution = new 找到小镇的法官_997();
        int ans = solution.findJudge(2, new int[][]{
                {
                        1, 2}
        });
        System.out.println(ans);
    }

    public static void test2() {
        找到小镇的法官_997 solution = new 找到小镇的法官_997();
        int ans = solution.findJudge(3, new int[][]{
                {1, 3},
                {2, 3}
        });
        System.out.println(ans);
    }

    public static void test3() {
        找到小镇的法官_997 solution = new 找到小镇的法官_997();
        int ans = solution.findJudge(3, new int[][]{
                {1, 3},
                {2, 3},
                {3, 1},
        });
        System.out.println(ans);
    }
}
