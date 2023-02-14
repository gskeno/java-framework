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
     * 以索引为根节点的连通分图内节点个数
     */
    private int[] size;

    /**
     * 连通分量个数
     */
    private int setCount;

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
        this.setCount = n;
        size = new int[n];
        // 初始时，每个节点都是一个连通图，图内节点个数为都是1
        for (int i = 0; i < n; i++) {
            size[i] = 1;
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


        int sizeX = size[parentX];
        int sizeY = size[parentY];
        // 尝试将小树挂载到大树上
        if (sizeX < sizeY){
            size[parentY] += sizeX;
            parents[parentX] = parentY;
        }else {
            size[parentX] += sizeY;
            parents[parentY] = parentX;
        }
        // 每次连接，都会时连通分量个数--
        this.setCount--;
    }

    /**
     * 连通分量个数
     * @return
     */
    public int getSetCount() {
        return setCount;
    }
}
