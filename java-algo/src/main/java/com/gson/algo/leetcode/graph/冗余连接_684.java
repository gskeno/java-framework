package com.gson.algo.leetcode.graph;

import java.util.Arrays;

public class 冗余连接_684 {

    public int[] findRedundantConnection(int[][] edges) {

        int maxNodes = edges.length;

        // 初始化，每个节点都是一个连通分量
        // 表示 i节点的父节点就是i节点自身
        int[] parents = new int[maxNodes + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }


        for (int[] edge : edges) {
            int node0 = edge[0];
            int node1 = edge[1];

            // 如果node0与node1不连通，使之连通
            if (!connected(parents, node0, node1)) {
                union(parents, node0, node1);
            } else {
                return edge;
            }
        }
        return new int[]{0};
    }

    /**
     * 使node0与node1连通
     */
    public void union(int[] parents, int node0, int node1) {
        int parent0 = parents[node0];
        while (parent0 != parents[parent0]) {
            parent0 = parents[parent0];
        }

        int parent1 = parents[node1];
        while (parent1 != parents[parent1]) {
            parent1 = parents[parent1];
        }
        parents[parent0] = parent1;
    }

    /**
     * 判断两个节点是否相连，则判断其是否是一个父节点即可
     * @param parents
     * @param node0
     * @param node1
     * @return
     */
    public boolean connected(int[] parents, int node0, int node1){
        int parentA = find(parents, node0);
        int parentB = find(parents, node1);
        return parentA == parentB;
    }

    /**
     * 一直递归，找到node的父节点
     * @param parents
     * @param node
     * @return
     */
    public int findParent(int[] parents, int node){
        while (node != parents[node]){
            node = parents[node];
        }
        return node;
    }

    /**
     * 找node节点的终极父亲，且将父亲设置为终极父亲
     * 遍历到的节点，也做同样的设置
     * @param parents
     * @param node
     * @return
     */
    public int find(int[] parents, int node){
        if (node != parents[node]){
            parents[node] = find(parents, parents[node]);
        }
        return parents[node];
    }

    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        冗余连接_684 solution = new 冗余连接_684();
        int[] ans = solution.findRedundantConnection(new int[][]{
                {1, 2},
                {1, 3},
                {2, 3}
        });
        System.out.println(Arrays.toString(ans));
    }

    public static void test2() {
        冗余连接_684 solution = new 冗余连接_684();
        int[] ans = solution.findRedundantConnection(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}
        });
        System.out.println(Arrays.toString(ans));
    }
}
