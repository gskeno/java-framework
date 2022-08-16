package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/network-delay-time/
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 */
public class 网络延迟时间 {
    /**
     * n个节点,
     * times[i][j]表示节点i到节点j的值
     * 第k个节点作为源节点
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 表示节点间距离无穷大  infinite
        int INF = Integer.MAX_VALUE/2;
        // 邻接矩阵表示节点i到节点j的距离
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], INF);
        }
        // 这里将节点编号都-1,使节点编号区间为[0, n-1]
        for(int[] time : times){
            int i = time[0] - 1;
            int j = time[1] - 1;
            grid[i][j] = time[2];
        }
        // 源节点k-1 到节点i 的最短路径长度为dist[i],很明显存在dist[k-1] = 0;
        // 其他节点到源节点最短路径长度为 +∞
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k-1] = 0;
        // userd[i]=true 表示已经确定了节点i到源点的最小路径长度
        boolean[] used = new boolean[n];
        // 执行n次，每次确定一个节点到源点的最小路径长度
        for (int i = 0; i < n; i++) {
            // 应该确定哪个节点到源点的最小路径长度呢？
            // 从[未确定最短路径长度节点集合]U中找离源点路径长度最短的x节点，将x加入到S集合
            // 同时更新所有节点到源点的最短路径长度
            int x = -1;
            for (int y = 0; y < n; y++) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])){
                    x = y;
                }
            }
            // x是U中距离源点最短路径长度最小的点
            used[x] = true;
            // 更新所有节点到源点最短路径长度
            // 其实这里只更新x相邻且在U中的节点 到源点最短路径长度即可
            for (int y = 0; y < n; y++) {
                dist[y] = Math.min(dist[y], dist[x] + grid[x][y]);
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        网络延迟时间 solution = new 网络延迟时间();
        int ans = solution.networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        }, 4, 2);
        System.out.println(ans);
    }
}
