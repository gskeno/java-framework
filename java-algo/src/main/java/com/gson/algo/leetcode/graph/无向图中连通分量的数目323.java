package com.gson.algo.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。
 *
 * 解法1: 深度优先搜索
 */
public class 无向图中连通分量的数目323 {

    List<Integer>[] table;
    private boolean[] visited;
    private int ans;
    /**
     * 递归
     * @param n
     * @param edges
     * @return
     */
    public int countComponents(int n, int[][] edges){
        ans = 0;
        // 邻接表存储图
        table = new List[n];
        // 访问记录
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            table[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            int fromV = edge[0];
            int toV = edge[1];
            // 无向图
            table[fromV].add(toV);
            table[toV].add(fromV);
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                ans++;
                visited[i] = true;
                dfs(i);
            }
        }
        return ans;
    }

    /**
     * 深度遍历
     * @param nodeIdx
     */
    private void dfs(int nodeIdx){
        for (Integer neighborhoodId : table[nodeIdx]) {
            if (!visited[neighborhoodId]){
                visited[neighborhoodId] = true;
                dfs(neighborhoodId);
            }
        }
    }

    public static void main(String[] args) {
        无向图中连通分量的数目323 solution = new 无向图中连通分量的数目323();
        int ans = 0;
        ans = solution.countComponents(5, new int[][]{{0,1},{1,2},{3,4}});
        System.out.println(ans);
        ans = solution.countComponents(5, new int[][]{{0,1},{1,2},{2,3},{3,4}});
        System.out.println(ans);
    }
}
