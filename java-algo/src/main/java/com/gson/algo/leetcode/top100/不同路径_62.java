package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/unique-paths/
 */
public class 不同路径_62 {

    public int uniquePaths(int m, int n) {
        return uniquePaths(1, 1, m, n);
    }

    public int uniquePaths(int line, int col, int m, int n){
        // 可以选择往下走，也可以选择往右走
        if (line < m && col < n){
            // 选择往下走,往右走
            return uniquePaths(line + 1, col, m, n)
                    + uniquePaths(line, col+1, m, n);
        }
        // 只能往右走
        else if (line == m){
            return 1;
        }
        // 只能往下走
        else if (col == n){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        不同路径_62 solution = new 不同路径_62();
        int ans = solution.uniquePaths(3, 2);
        System.out.println(ans);

        ans = solution.uniquePaths(3, 3);
        System.out.println(ans);

        ans = solution.uniquePaths(51, 9);
        System.out.println(ans);
    }

}
