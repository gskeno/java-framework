package com.gson.algo.leetcode.graph;

/**
 * https://leetcode.cn/problems/number-of-operations-to-make-network-connected/
 *
 */
public class 连通网络的操作次数 {
    /**
     * 提示1: M个独立的区块 需要M-1条线才能相互连接
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n -1){
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        for(int[] con : connections){
            unionFind.union(con[0], con[1]);
        }
        int setCount = unionFind.getSetCount();
        return setCount - 1;
    }

    public static void main(String[] args) {
        连通网络的操作次数 solution = new 连通网络的操作次数();
        int ans = solution.makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}});
        System.out.println(ans);
    }
}
