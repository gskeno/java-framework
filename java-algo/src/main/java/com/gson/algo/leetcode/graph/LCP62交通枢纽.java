package com.gson.algo.leetcode.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/D9PW8w/
 * 统计入度和出度
 */
public class LCP62交通枢纽 {

    /**
     * 统计入度和出度
     * @param path
     * @return
     */
    public int transportationHub(int[][] path) {
        // 每个定点的入度和出度
        int[] inDegrees = new int[1001];
        int[] outDegrees = new int[1001];
        // 顶点个数
        Set<Integer> vertices = new HashSet<>();
        for(int[] p : path){
            outDegrees[p[0]]++;
            inDegrees[p[1]]++;
            vertices.add(p[0]);
            vertices.add(p[1]);
        }
        for(int vertex : vertices){
            if (inDegrees[vertex] == vertices.size() - 1 && outDegrees[vertex] == 0){
                return vertex;
            }
        }
        return -1;
    }
}
