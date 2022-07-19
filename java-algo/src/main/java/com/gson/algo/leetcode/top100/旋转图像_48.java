package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/rotate-image/
 * <p>
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class 旋转图像_48 {

    /**
     * 将旋转 翻译为翻转
     * <p>
     * 先上下翻转(水平翻转)
     * 再主对角线翻转
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }

        // 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        旋转图像_48 solution = new 旋转图像_48();
        solution.rotate(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
    }

}
