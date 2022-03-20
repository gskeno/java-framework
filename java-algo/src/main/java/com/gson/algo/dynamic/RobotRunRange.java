package com.gson.algo.dynamic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8
 * 机器人运动范围
 * 地上有一个 rows 行和 cols 列的方格。坐标从 [0,0] 到 [rows-1,cols-1] 。
 * 一个机器人从坐标 [0,0] 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于 threshold 的格子。
 * 例如，当 threshold 为 18 时，机器人能够进入方格   [35,37] ，
 * 因为 3+5+3+7 = 18。但是，它不能进入方格 [35,38] ，
 * 因为 3+5+3+8 = 19 。请问该机器人能够达到多少个格子？
 */
public class RobotRunRange {
    /**
     * 深度遍历 OR 广度遍历
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return dfs(visited, 0, 0, rows, cols, threshold);
    }



    private int dfs(boolean[][] visited, int i, int j, int rows, int cols, int threshold){
        if (i < 0 || j <0 || i >= rows || j >= cols || visited[i][j] || sum(i,j) > threshold){
            return 0;
        }
        visited[i][j] = true;
        return 1
                + dfs(visited, i+1, j, rows, cols, threshold)
                + dfs(visited, i, j+1, rows, cols, threshold);
    }
    private int sum(int i, int j){
        int sum = 0;
        while (i>0){
            sum += i%10;
            i = i/10;
        }
        while (j>0){
            sum += j%10;
            j = j/10;
        }
        return sum;
    }

    /**
     * 广度优先遍历
     * @return
     */
    private int bfs(int rows, int cols, int threshold){
        Queue<int[]> queue = new LinkedList<>();
        // 先将左上节点push进队列
        queue.add(new int[]{0,0});
        int count = 1;
        boolean[][] visited = new boolean[rows][cols];
        while (!queue.isEmpty()){
            int[] ele = queue.poll();
            int i = ele[0];
            int j = ele[1];
            // 将当前节点的下方节点加进队列
            if (i+1 < rows && j < cols && sum(i+1,j) <= threshold && !visited[i+1][j]){
                queue.add(new int[]{i+1,j});
                visited[i+1][j] = true;
                count++;
            }
            // 将当前节点的右方节点加进队列
            if (i < rows && j + 1 < cols && sum(i, j+1) <= threshold && !visited[i][j+1]){
                queue.add(new int[]{i, j+1});
                visited[i][j+1] = true;
                count++;
            }
        }
        return count;
    }
}
