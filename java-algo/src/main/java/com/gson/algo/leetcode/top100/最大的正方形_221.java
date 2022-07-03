package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/maximal-square/
 */
public class 最大的正方形_221 {


    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 遍历到每个元素时，考虑该元素是最大正方形的左上角
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int area = getArea(matrix, i, j, rows - 1, cols - 1);
                max = Math.max(area, max);
            }
        }
        return max;

    }

    /**
     * 计算在 [rowTop, colLeft]为左上点A，[rowBottom, colRight] B 为右下点界限内的最大正方形，
     * 正方形的左上点必须是A,右下点任意
     *
     * @param rowTop
     * @param colLeft
     * @param rowBottom
     * @param colRight
     * @return
     */
    public int getArea(char[][] matrix, int rowTop, int colLeft, int rowBottom, int colRight) {
        if (colRight >= matrix[0].length) {
            colRight = matrix[0].length - 1;
        }
        if (rowBottom >= matrix.length) {
            rowBottom = matrix.length - 1;
        }
        for (int i = rowTop; i <= rowBottom; i++) {
            for (int j = colLeft; j <= colRight; j++) {
                System.out.println(i + "," + j);
                if (matrix[i][j] == '0') {
                    int dist = Math.max(i - 1 - rowTop, j - 1 - colLeft);
                    return getArea(matrix, rowTop, colLeft, rowTop + dist, colLeft + dist);
                }
            }
        }
        return (int) Math.pow(Math.min(rowBottom - rowTop + 1, colRight - colLeft + 1), 2);
    }

    public static void main(String[] args) {
        最大的正方形_221 solution = new 最大的正方形_221();
//        char[][] matrix = new char[1][1];
//        matrix[0] = new char[]{'1'};

//        char[][] matrix = new char[4][5];
//        matrix[0] = new char[]{'1', '0', '1', '0', '0'};
//        matrix[1] = new char[]{'1', '0', '1', '1', '1'};
//        matrix[2] = new char[]{'1', '1', '1', '1', '1'};
//        matrix[3] = new char[]{'1', '0', '0', '1', '0'};


//        char[][] matrix = new char[2][2];
//        matrix[0] = new char[]{'0', '1'};
//        matrix[1] = new char[]{'1', '0'};

        char[][] matrix = new char[][]{
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '0', '0', '0'},
                {'0', '1', '1', '1', '1', '0', '0', '0'}
        };
        System.out.println(solution.maximalSquare(matrix));

    }
}
