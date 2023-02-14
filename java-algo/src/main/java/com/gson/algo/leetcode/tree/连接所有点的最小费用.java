package com.gson.algo.leetcode.tree;

import com.gson.algo.leetcode.graph.UnionFind;

import java.util.*;

/**
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/
 */
public class 连接所有点的最小费用 {

    /**
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        List<Edge> edgeList = new ArrayList<>();
        int V = points.length;
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                edgeList.add(new Edge(dist(points, i, j), i, j));
            }
        }
        // 按照节点距离 由短到长排序
        Collections.sort(edgeList, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.dist - o2.dist;
            }
        });

        UnionFind unionFind = new UnionFind(V);
        int num = 1;
        int ret = 0;
        for(Edge edge : edgeList){
            int x = edge.x;
            int y = edge.y;
            if (!unionFind.connected(x, y)){
                unionFind.union(x, y);
                num++;
                ret += edge.dist;

            }
            if (num == V){
                break;
            }
        }
        return ret;
    }

    private int dist(int[][] points, int i, int j){
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }

    class Edge{
        int dist;
        // 两个节点id
        int x;
        int y;

        Edge(int dist, int x, int y){
            this.dist = dist;
            this.x = x;
            this.y = y;
        }

    }
}
