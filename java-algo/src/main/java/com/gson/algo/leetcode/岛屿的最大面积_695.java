package com.gson.algo.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/max-area-of-island/
 */
public class 岛屿的最大面积_695 {

    private int lines;
    private int cols;

    /**
     * 深度优先遍历
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        lines = grid.length;
        cols = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    /**
     * 从i,j开始走，如果是岛屿，需要递归继续走,走过的地方都置为水域，返回岛屿的面积
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int dfs(int[][] grid, int i, int j) {
        int ans = 0;
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            ans++;
        }

        if (i - 1 >= 0) {
            ans += dfs(grid, i - 1, j);
        }

        if (i + 1 < lines) {
            ans += dfs(grid, i + 1, j);
        }
        if (j - 1 >= 0) {
            ans += dfs(grid, i, j - 1);
        }
        if (j + 1 < cols) {
            ans += dfs(grid, i, j + 1);
        }
        return ans;
    }

    /**
     * 广度优先遍历
     * 从任意一个岛屿出发，先将岛屿放置到队列中去，同时将岛屿置为水域；
     * <p>
     * 每次从队列中抽取头部元素，如果其周围也是岛屿，将其周围岛屿置为水域，同时将岛屿放置到队列中去
     * <p>
     * 当队列为空时，继续遍历矩阵，找寻下一个岛屿入口
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland1(int[][] grid) {
        lines = grid.length;
        cols = grid[0].length;
        int maxArea = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0;
                    int num = 0;
                    while (!queue.isEmpty()) {
                        num++;
                        int[] position = queue.poll();
                        int x = position[0];
                        int y = position[1];
                        if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                            grid[x - 1][y] = 0;
                            queue.offer(new int[]{x - 1, y});
                        }
                        if (x + 1 < lines && grid[x + 1][y] == 1) {
                            grid[x + 1][y] = 0;
                            queue.offer(new int[]{x + 1, y});
                        }
                        if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                            grid[x][y - 1] = 0;
                            queue.offer(new int[]{x, y - 1});
                        }
                        if (y + 1 < cols && grid[x][y + 1] == 1) {
                            grid[x][y + 1] = 0;
                            queue.offer(new int[]{x, y + 1});
                        }
                    }
                    maxArea = Math.max(maxArea, num);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        岛屿的最大面积_695 solution = new 岛屿的最大面积_695();
        int[][] island = new int[][]{
                {
                        0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {
                        0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {
                        0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {
                        0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {
                        0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0
                }
        };
        int ans = solution.maxAreaOfIsland1(island);
        System.out.println(ans);
    }


}
