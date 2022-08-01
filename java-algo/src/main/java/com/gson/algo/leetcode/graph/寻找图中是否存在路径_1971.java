package com.gson.algo.leetcode.graph;

/**
 *https://leetcode.cn/problems/find-if-path-exists-in-graph/
 */
public class 寻找图中是否存在路径_1971 {
    // 0表示未被访问过
    int[] visit;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        visit = new int[n];
        return helper(edges, source, destination);
    }

    public boolean helper(int[][] edges, int start,  int end){
        boolean ans = false;
        for(int[] edge : edges){
            if (edge[0] == start && edge[1] == end){
                return true;
            }
            if (edge[1] == start && edge[0] == end){
                return true;
            }
            if (edge[0] == start && visit[start] == 0){
                visit[start] = 1;
                ans = helper(edges, edge[1], end);
                if (ans){
                    return true;
                }
                visit[start] = 0;
            }

            if (edge[1] == start && visit[start] == 0){
                visit[start] = 1;
                ans = helper(edges, edge[0], end);
                if (ans){
                    return true;
                }
                visit[start] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        寻找图中是否存在路径_1971 solution = new 寻找图中是否存在路径_1971();
        boolean ans = solution.validPath(3, new int[][]{
                {0, 1},
                {1, 2},
                {2, 0}
        }, 0, 2);
        System.out.println(ans);
    }

    public static void test2(){
        寻找图中是否存在路径_1971 solution = new 寻找图中是否存在路径_1971();
        boolean ans = solution.validPath(6, new int[][]{
                {0, 1},
                {0, 2},
                {3, 5},
                {5, 4},
                {4, 3},
                {2, 0}
        }, 0, 5);
        System.out.println(ans);
    }


}
