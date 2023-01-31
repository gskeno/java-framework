package com.gson.algo.leetcode.graph;

/**
 * 并查集
 */
public class UnionFind {

    /**
     * 节点编号的父节点
     */
    private int[] parents;

    /**
     * n个节点，节点编号0至(n-1)
     * @param n
     */
    public UnionFind(int n){
        parents = new int[n];
        // 初始时，每个节点的父节点都是自身
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    /**
     * 判断两个节点是否相连
     * @param x
     * @param y
     * @return
     */
    public boolean connected(int x, int y){
        return find(x) == find(y);
    }

    /**
     * 查找节点的父节点
     * @param i
     * @return
     */
    public int find(int i){
        // 一直向上查询，直至查到根节点
        while (parents[i] != i){
            i = parents[i];
        }
        return parents[i];
    }

    /**
     * 连接两个节点
     * @param x
     * @param y
     */
    public void union(int x, int y){
        // 两个节点已经连通，不处理
        if (connected(x, y)){
            return;
        }
        int parentX = find(x);
        int parentY = find(y);
        // 两个节点的根节点相连
        parents[parentX] = parentY;
    }

    public boolean hasCycle(int[][] edges){
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            if (connected(from, to)){
                return true;
            }

            union(from, to);
        }

        return false;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(3);
        boolean hasCycle = unionFind.hasCycle(new int[][]{{0, 1}, {1, 2}, {0, 2}});
        System.out.println(hasCycle);
    }
}
