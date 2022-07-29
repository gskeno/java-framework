package com.gson.algo.interview;

/**
 * 一个正方形矩阵，元素值
 * 为0， 表示可以通过
 * 为1, 表示桌子，不可以通过
 * 为2，表示一盏灯，可以通过，灯可以被熄灭
 * 为3，表示用户工位
 * 为4，表示出口
 * <p>
 * 用户需要在指定时间内，走到出口，才能坐上班车。
 * 用户只能向上，下，左，右走动，每走动一次消耗1s
 * 求用户最多能熄灭几盏灯。
 * <p>
 * 如果用户无法走到出口，则返回-1
 */
public class 指定时间内到达终点所能关闭最多几盏灯 {

    private boolean[][] visited;

    private int lines;

    private int cols;

    private int maxLights = -1;

    int[] start;

    int[] end;

    public int getLights(int[][] grid, int timeLimit) {
        lines = grid.length;
        cols = grid[0].length;
        visited = new boolean[lines][cols];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 3) {
                    start = new int[]{i, j};
                } else if (grid[i][j] == 4) {
                    end = new int[]{i, j};
                }

                if (start != null && end != null) {
                    break;
                }
            }
        }

        // 深度优先搜索，走timeLimit步，当走完时，如果在终点，看其能够熄灭多少盏灯；
        // 找到所有路径中能熄灭最多盏灯的方案
        dfs(grid, start[0], start[1], timeLimit, 0);
        return maxLights;
    }

    /**
     * i,j 位置是可走的
     *
     * @param grid
     * @param i
     * @param j
     * @param times
     * @param lights
     */
    public void dfs(int[][] grid, int i, int j, int times, int lights) {
        // 是灯，且没经历过
        if (grid[i][j] == 2 && !visited[i][j]) {
            visited[i][j] = true;
            lights++;
        }
        // 走完了所有时间，则不再递归
        if (times == 0) {
            // 走到终点
            if (i == end[0] && j == end[1]) {
                maxLights = Math.max(maxLights, lights);
            }
            return;
        }
        // 尝试向上走
        if (i - 1 >= 0 && grid[i - 1][j] != 1) {
            dfs(grid, i - 1, j, times - 1, lights);
            visited[i - 1][j] = false;
        }

        // 尝试向下走
        if (i + 1 < lines && grid[i + 1][j] != 1) {
            dfs(grid, i + 1, j, times - 1, lights);
            visited[i + 1][j] = false;
        }

        // 尝试向左走
        if (j - 1 >= 0 && grid[i][j - 1] != 1) {
            dfs(grid, i, j - 1, times - 1, lights);
            visited[i][j - 1] = false;
        }

        // 尝试向右走
        if (j + 1 < cols && grid[i][j + 1] != 1) {
            dfs(grid, i, j + 1, times - 1, lights);
            visited[i][j + 1] = false;
        }

        // 尝试不走
        dfs(grid, i, j, times - 1, lights);
    }

}
