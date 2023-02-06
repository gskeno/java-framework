package com.gson.algo.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/01-matrix/
 */
public class _01矩阵_542 {

    /**
     * 广度优先遍历
     *
     * 初始: 将所有0元素坐标放置到队列中
     *
     * 遍历: 从队列中取出头元素，遍历头元素的上下左右4个坐标元素v(坐标位置不合法，忽略掉;
     * 坐标已经访问过，忽略掉), 设置grid[dx][dy] = grid[x][y]+1;
     * @param mat
     * @return
     */
    private int[][] directions = new int[][]{{1,0}, {-1,0}, {0,-1},{0,1}};

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] grid = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0){
                    queue.offer(new int[]{i,j});
                    visit[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < directions.length; i++) {
                int[] direction = directions[i];
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (nx < 0 || nx >= m || ny <0 || ny >=n || visit[nx][ny]){
                    continue;
                }
                grid[nx][ny] = grid[x][y] +1;
                visit[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
        return grid;
    }

    public static void main(String[] args) {

    }


}
