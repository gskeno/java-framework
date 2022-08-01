package com.gson.algo.leetcode.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/
 * 拓扑排序
 */
public class 有向无环图中一个节点的所有祖先_2192 {

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // 记录节点的出边
        List<List<Integer>> outEdges = IntStream.range(0, n).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        // 记录每个节点的入度
        int[] degree = new int[n];
        // 记录每个节点的祖先集合
        Map<Integer, Set<Integer>> anc = new HashMap<>();

        for (int[] edge : edges) {
            // 节点入度+1
            degree[edge[1]]++;
            // 节点出边变多
            outEdges.get(edge[0]).add(edge[1]);
        }

        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为0的节点加入到列表
        for (int idx = 0; idx < n; idx++) {
            if (degree[idx] == 0) {
                queue.offer(idx);
                anc.put(idx, new HashSet<>());
            }
        }

        while (!queue.isEmpty()) {
            Integer u = queue.poll();
            List<Integer> vs = outEdges.get(u);
            Set<Integer> ancU = anc.get(u);
            for (int v : vs) {
                Set<Integer> ancV = anc.get(v);
                if (ancV == null){
                    ancV = new HashSet<>();
                }
                ancV.addAll(new ArrayList<>(ancU));
                ancV.add(u);
                anc.put(v, ancV);
                degree[v]--;
                if (degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> parents = new ArrayList<>(anc.get(i));
            Collections.sort(parents);
            res.add(parents);
        }
        return res;
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        有向无环图中一个节点的所有祖先_2192 solution = new 有向无环图中一个节点的所有祖先_2192();
        List<List<Integer>> res = solution.getAncestors(8, new int[][]{
                {0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}
        });
        System.out.println(res);
    }
}
