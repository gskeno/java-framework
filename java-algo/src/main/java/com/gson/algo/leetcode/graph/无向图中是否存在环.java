package com.gson.algo.leetcode.graph;

public class 无向图中是否存在环 {
    public boolean hasCycle(int n , int[][] edges){
        UnionFind unionFind = new UnionFind(n);
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            if (unionFind.connected(from, to)){
                return true;
            }

            unionFind.union(from, to);
        }
        return false;
    }

    public static void main(String[] args) {
        无向图中是否存在环 solution = new 无向图中是否存在环();
        boolean hasCycle = solution.hasCycle(3, new int[][]{{0, 1}, {1, 2}, {0, 2}});
        System.out.println(hasCycle);
    }
}
