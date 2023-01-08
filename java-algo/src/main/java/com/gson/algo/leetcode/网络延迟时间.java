package com.gson.algo.leetcode;

import java.util.*;

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
            grid[i][i] = 0;
        }
        // 这里将节点编号都-1,使节点编号区间为[0, n-1]
        for(int[] time : times){
            int i = time[0] - 1;
            int j = time[1] - 1;
            grid[i][j] = time[2];
        }
        // 源节点k-1 到节点i 的最短路径长度为dist[i],很明显存在dist[k-1] = 0;
        // 即源点到源点的路径长度为0
        // 源点到其他节点的 最短路径长度为 +∞
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k-1] = 0;

        // used[i] = true 表示已经确定了 源点到节点i 的最小路径长度
        boolean[] used = new boolean[n];
        // 执行n次，每次确定一个 源点到节点x 的最小路径长度
        for (int i = 0; i < n; i++) {
            // 应该确定  源点到哪个节点  的最小路径长度呢？
            // 从[未确定最短路径长度节点集合]U中  找 源点距之路径长度最短的x节点， 将x加入到S集合
            // 同时更新 源点到U中所有节点 的最短路径长度
            int x = -1;
            for (int y = 0; y < n; y++) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])){
                    x = y;
                }
            }
            // 源点 距离集合U 路径长度最短的节点是x
            used[x] = true;
            // 更新 源点到U中所有节点 的最短路径长度
            // 其实这里只关心 x相邻且在U中的节点
            for (int y = 0; y < n; y++) {
                dist[y] = Math.min(dist[y], dist[x] + grid[x][y]);
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    /**
     * n个节点,
     * times[i][j]表示节点i到节点j的值
     * 第k个节点作为源节点
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        // 使用邻接表 来存储图
        // table[i]表示以i节点为起点, 节点编号从0开始标记,
        // 元素值是一个int[] 数组列表, 每一个元素int[],表示一个节点,设置为M。
        // 节点i到节点M(编号为M[0])的最短路径长度为M[1]
        List<int[]>[] table = new List[n];
        for (int i = 0; i < n; i++) {
            table[i] = new ArrayList<>();
        }
        for(int[] time : times){
            // x到y节点的距离为dist
            int x = time[0] - 1;
            int y = time[1] - 1;
            int dist = time[2];
            // 表示x节点到y节点的距离为dist
            table[x].add(new int[]{y, dist});
        }
        // 元素类型为int[], 表示的是一个节点，该节点到源点的最短路径长度为int[0], 该节点的id为int[1]
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 以节点到源点的最短路径长度做最小堆
                return o1[0] - o2[0];
            }
        });
        queue.offer(new int[]{0, k-1});
        // dist[i]表示i节点到源点的最短路径长度，初始化都为无穷大
        int[] dist = new int[n];
        int INF = Integer.MAX_VALUE/2;
        Arrays.fill(dist, INF);
        // 源点到源点的最短路径长度为0
        dist[k-1] = 0;

        while (!queue.isEmpty()){
            int[] nearestNode = queue.poll();
            // 最近节点到源点的距离
            int nearDist = nearestNode[0];
            // 最近节点的id
            int x = nearestNode[1];

            // 剪枝，这里表示从队列中取出的节点 距源点最短路径长度dist[x] 比当初offer进队列时小，
            // 就是最短路径长度已经被更新过，这里不再更新了，因为同一个节点，可能多次进队列。
            // 比如源点A到节点B的距离为1，A到C的距离为5,B到C的距离为2。poll A时，因为B,C与A相邻,
            // 所以B(1),C(5)进队列；下一次会poll B, 更新C的最短路径长度为C(3)，且offer C(3)。
            // 至此队列中有C(5),C(3)。C(3)先出队列，C(5)再出队列时，发现C到源点的最短路径长度已经
            // 更新为3，自己的5已经失效，所以跳过
            if (dist[x] < nearDist){
                continue;
            }

            // x节点的相邻节点
            for(int[] neighbor : table[x]){
                int neighborId = neighbor[0];
                int neighborDist = neighbor[1];
                if (nearDist + neighborDist < dist[neighborId]){
                    dist[neighborId] = nearDist + neighborDist;
                    // 这里可能导致同一个节点多次进队列
                    queue.offer(new int[]{dist[neighborId], neighborId});
                }
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        网络延迟时间 solution = new 网络延迟时间();
        int ans = solution.networkDelayTime1(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        }, 4, 2);
        System.out.println(ans);
    }

    public static void test2(){
        网络延迟时间 solution = new 网络延迟时间();
        int ans = solution.networkDelayTime1(new int[][]{
                {1,2,1}
        }, 2, 2);
        System.out.println(ans);
    }
}
