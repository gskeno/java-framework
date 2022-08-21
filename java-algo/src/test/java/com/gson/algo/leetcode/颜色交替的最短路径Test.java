package com.gson.algo.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class 颜色交替的最短路径Test {
    private 颜色交替的最短路径 solution = new 颜色交替的最短路径();

    @Test
    public void test1() {
        int[] ans = solution.shortestAlternatingPaths(2, new int[][]{
                        {0, 0}
                },
                new int[][]{
                        {0, 1}
                });
        System.out.println(Arrays.toString(ans));
    }

    @Test
    public void test2() {
        int[] ans = solution.shortestAlternatingPaths(3, new int[][]{
                        {0, 1},
                        {1, 2}
                },
                new int[][]{
                });
        System.out.println(Arrays.toString(ans));
    }

    @Test
    public void test3() {
        int[] ans = solution.shortestAlternatingPaths(3, new int[][]{
                        {1, 0}
                },
                new int[][]{
                        {2, 1}
                });
        System.out.println(Arrays.toString(ans));
    }

    @Test
    public void test4() {
        int[] ans = solution.shortestAlternatingPaths(3, new int[][]{
                        {0, 1}
                },
                new int[][]{
                        {1, 2}
                });
        System.out.println(Arrays.toString(ans));
    }

    @Test
    public void test5() {
        int[] ans = solution.shortestAlternatingPaths(3, new int[][]{
                        {0, 1},
                        {0, 2}
                },
                new int[][]{
                        {1, 0}
                });
        System.out.println(Arrays.toString(ans));
    }

    @Test
    public void test6() {
        int[] ans = solution.shortestAlternatingPaths(5, new int[][]{
                        {3, 2},
                        {4, 1},
                        {1, 4},
                        {2, 4}
                },
                new int[][]{
                        {2, 3},
                        {0, 4},
                        {4, 3},
                        {4, 4},
                        {4, 0},
                        {1, 0},
                });
        System.out.println(Arrays.toString(ans));
    }
}
