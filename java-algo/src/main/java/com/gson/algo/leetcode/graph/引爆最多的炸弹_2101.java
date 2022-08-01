package com.gson.algo.leetcode.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.cn/problems/detonate-the-maximum-bombs/
 */
public class 引爆最多的炸弹_2101 {

    public int maximumDetonation(int[][] bombs) {
        // 构建有向图，i j 为不同炸弹
        List<List<Integer>> graph = IntStream.range(0, bombs.length).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        for(int i = 0; i < bombs.length; i++) {
            for(int j = 0; j < bombs.length; j++) {
                if(i != j && isDetonates(i, j, bombs)) {
                    graph.get(i).add(j);
                }
            }
        }

        int ans = 0;
        // 遍历每一个炸弹，更新能引爆的最大数量
        for(int i = 0; i < bombs.length; i++) {
            ans = Math.max(dfs(i, graph, new boolean[bombs.length]), ans);
        }

        return ans;
    }

    private int dfs(int u, List<List<Integer>> graph, boolean[] visited) {
        visited[u] = true;
        // 当流中没有满足过滤条件的元素的时候终止
        return 1 + graph.get(u).stream().filter(v -> !visited[v]).mapToInt(v -> dfs(v, graph, visited)).sum();
    }

    // isDetonates：判断 炸弹i 能否相互引爆 炸弹j
    private boolean isDetonates(int i, int j, int[][] bombs) {
        long x = bombs[j][0] - bombs[i][0], y = bombs[j][1] - bombs[i][1];
        return x * x + y * y <= (long) bombs[i][2] * bombs[i][2];
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        引爆最多的炸弹_2101 solution = new 引爆最多的炸弹_2101();
        int ans = solution.maximumDetonation(new int[][]{
                {1, 2, 3},
                {2, 3, 1},
                {3, 4, 2},
                {4, 5, 3},
                {5, 6, 4}
        });
        System.out.println(ans);
    }
}
