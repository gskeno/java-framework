package com.gson.algo.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/course-schedule/
 * <p>
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程ai 则 必须 先学习课程 bi 。
 * <p>
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 经典有向图，拓扑排序
 */
public class 课程表_207 {

    /**
     * 有向图，idx为箭头的出发点，val(是个list)是箭头的终点，
     * 表示idx指向了val集合的各个点
     */
    List<List<Integer>> edges;

    /**
     * 值为0，表示[未被搜索过]
     * 值为1，表示[搜索中]
     * 值为2，表示[搜索完成],已放入栈中
     */
    int[] visited;
    /**
     * 是否有效，当出现环时无效
     */
    boolean valid = true;


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];

        // 初始化有向图
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }

        for (int i = 0; i < numCourses && valid; i++) {
            // 表示未被访问过，要被处理
            if (visited[i] == 0){
                dfs(i);
            }
        }

        return valid;
    }

    public void dfs(int u){
        // 置为搜索中
        visited[u] = 1;
        // 遍历其指向节点
        List<Integer> vs = edges.get(u);
        for(int v : vs){
            if (visited[v] == 0){
                dfs(v);
                if (!valid){
                    return;
                }
            }
            // 出现环
            else if (visited[v] == 1){
                valid = false;
                return;
            }
        }
        // u指向的节点全部遍历完之后，将u置为已完成搜索
        visited[u] = 2;
    }
}
