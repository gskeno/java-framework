package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/cheapest-flights-within-k-stops/
 * 
 * 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，
 * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，
 * 使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 *
 */
public class K站中转内最便宜的航班 {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 初始化邻接表,存储图
        List<int[]>[] table = new List[n];
        for (int i = 0; i < n; i++) {
            table[i] = new ArrayList<>();
        }
        for(int[] flight : flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            table[from].add(new int[]{to, price});
        }
        // 节点到源点的最短路径长度
        int[] dist = new int[n];
        int INF = Integer.MAX_VALUE/2;
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Bellman-Ford算法
        // 松弛k次
        for (int i = 0; i < k + 1 ; i++) {
            int[] clone = dist.clone();
            for (int j = 0; j < n; j++) {
                // 节点j出发的相邻边
                List<int[]> neighbors = table[j];
                for(int[] neighbor : neighbors){
                    if (dist[j] + neighbor[1] < dist[neighbor[0]]){
                        dist[neighbor[0]] = clone[j] + neighbor[1];
                    }
                }
            }
        }

        return dist[dst] == INF ? -1 : dist[dst];

    }

    public static void main(String[] args) {
        K站中转内最便宜的航班 solution = new K站中转内最便宜的航班();
        int cheapestPrice = solution.findCheapestPrice(3, new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        }, 0, 2, 1);
        System.out.println(cheapestPrice);
    }
}
