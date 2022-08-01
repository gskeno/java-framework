package com.gson.algo.leetcode.graph;

/**
 * https://leetcode.cn/problems/route-between-nodes-lcci/
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * <p>
 * 示例1:
 * <p>
 * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 * 输出：true
 * <p>
 * 说明: 这里graph是一个二维数组。只有两列，即每行只有两个元素，A和B，表示可以从A点走到B点
 */
public class 节点间通路_04_01 {

    /**
     * 思路:
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        boolean[] visited = new boolean[n];
        return findWhetherExistsPath(visited, graph, start, target);
    }

    public boolean findWhetherExistsPath(boolean[] visited, int[][] graph, int start, int target) {
        int edges = graph.length;

        for (int i = 0; i < edges; i++) {
            // 已经找到一条有向边，可以由start到target，结束
            if (start == graph[i][0] && target == graph[i][1]) {
                return true;
            }
            // 已经有B可以到C，且找到A到B的有向边，则存在A到C的有向边
            if (target == graph[i][1] && ! visited[target]) {
                visited[target] = true;
                if (findWhetherExistsPath( visited, graph, start, graph[i][0])){
                    return true;
                }
                visited[target] = false;
            }
        }
        // 如果没有一条有向边到target，则返回false
        return false;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        节点间通路_04_01 solution = new 节点间通路_04_01();
        boolean whetherExistsPath = solution.findWhetherExistsPath(3, new int[][]{
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 2}
        }, 0, 2);
        System.out.println(whetherExistsPath);
    }

    public static void test2() {
        节点间通路_04_01 solution = new 节点间通路_04_01();
        boolean whetherExistsPath = solution.findWhetherExistsPath(5, new int[][]{
                {0, 1},
                {0, 2},
                {0, 4},
                {0, 4},
                {0, 1},
                {1, 3},
                {1, 4},
                {1, 3},
                {2, 3},
                {3, 4},
                {0, 4}}, 0, 4);
        System.out.println(whetherExistsPath);
    }

    public static void test3() {
        节点间通路_04_01 solution = new 节点间通路_04_01();
        boolean whetherExistsPath = solution.findWhetherExistsPath(4, new int[][]{
                {0, 3},
                {3, 0},
                {1, 2}

        }, 1, 3);
        System.out.println(whetherExistsPath);
    }
}
