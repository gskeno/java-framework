package com.gson.algo.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/shortest-path-with-alternating-colors/
 */
public class 颜色交替的最短路径 {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redTables = new List[n];
        List<Integer>[] blueTables = new List[n];
        for (int i = 0; i < n; i++) {
            redTables[i] = new ArrayList<>();
            blueTables[i] = new ArrayList<>();
        }
        for(int[] redEdge : redEdges){
            int from = redEdge[0];
            int to = redEdge[1];
            redTables[from].add(to);
        }
        for(int[] blueEdge : blueEdges){
            int from = blueEdge[0];
            int to = blueEdge[1];
            blueTables[from].add(to);
        }

        // 元素是一个包含三个元素的数组, x,y,z；x表示节点id，y表示从源节点到x的符合题意的路径长度,
        // z表示这条路径最后一条边的颜色(0无色,1红色,2蓝色)。
        Queue<int[]> queue = new LinkedList<>();

        // 从源点到节点i且最后一条边为红色的最短距离
        int[] redDist = new int[n];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(redDist, INF);
        // 从源点到节点i且最后一条边为蓝色的最短距离
        int[] blueDist = new int[n];
        Arrays.fill(blueDist, INF);
        blueDist[0] = 0;


        queue.offer(new int[]{0,0,2});
        queue.offer(new int[]{0,0,1});
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int z = poll[2];
            // 到x节点的最后一条线段是蓝色，则下一条要是红色
            if (z == 2){
                for(int node : redTables[x]){
                    if (y + 1 < redDist[node]){
                        redDist[node] = y + 1;
                        queue.offer(new int[]{node, y + 1, 1});
                    }
                }
            }else {
                // 到x节点的最后一条线段是红色，则下一条要是蓝色
                for(int node : blueTables[x]){
                    if ( y + 1 < blueDist[node]){
                        blueDist[node] = y + 1;
                        queue.offer(new int[]{node, y+1 , 2});
                    }
                }
            }
        }
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Math.min(redDist[i], blueDist[i]);
            if (dist[i] == INF){
                dist[i] = -1;
            }
        }
        return dist;
    }


}
