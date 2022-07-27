package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/word-search/
 * <p>
 * <p>
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class 单词搜索_79 {

    /**
     * check(i,j,k)表示从i,j位置是否可以到达 word[k]-word[k+1]-...-word[last] 后缀子串
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int lines = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[lines][cols];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                if (check(i, j, 0, board, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(int i, int j, int k, char[][] board, String word, boolean[][] visited) {
        int lines = board.length;
        int cols = board[0].length;
        // 当前i,j位置元素与word[k]都不相等，直接false
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        // 当前i,j位置与word[k]相等，且k是word的最后一位元素，则满足
        if (k == word.length() - 1) {
            return true;
        }

        // 走当前
        visited[i][j] = true;

        // 尝试朝下走
        if (i + 1 < lines && i + 1 >= 0 && !visited[i + 1][j]) {
            if (check(i + 1, j, k + 1, board, word, visited)) {
                return true;
            }
        }
        // 尝试朝上走
        if (i - 1 < lines && i - 1 >= 0 && !visited[i - 1][j]) {
            if (check(i - 1, j, k + 1, board, word, visited)) {
                return true;
            }
        }

        // 超时朝左走
        if (j - 1 < cols && j - 1 >= 0 && !visited[i][j - 1]) {
            if (check(i, j - 1, k + 1, board, word, visited)) {
                return true;
            }
        }
        // 尝试朝右走
        if (j + 1 < cols && j + 1 >= 0 && !visited[i][j + 1]) {
            if (check(i, j + 1, k + 1, board, word, visited)) {
                return true;
            }
        }

        // 恢复状态
        visited[i][j] = false;

        return false;
    }

    public static void main(String[] args) {
        单词搜索_79 solution = new 单词搜索_79();
        System.out.println(solution.exist(new char[][]{{'a','a'}}, "aaa"));
    }
}
