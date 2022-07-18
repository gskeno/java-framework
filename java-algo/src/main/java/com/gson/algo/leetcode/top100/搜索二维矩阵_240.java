package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 */
public class 搜索二维矩阵_240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int lines = matrix.length;
        int cols = matrix[0].length;

        int down = lines - 1;
        int left = 0;
        while (down > -1 && left < cols){
            if (matrix[down][left] == target){
                return true;
            }

            if (matrix[down][left] > target){
                down--;
            }else {
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        搜索二维矩阵_240 solution = new 搜索二维矩阵_240();
//        boolean b = solution.searchMatrix(new int[][]{
//                        {1, 4, 7, 11, 15},
//                        {2, 5, 8, 12, 19},
//                        {3, 6, 9, 16, 22},
//                        {10, 13, 14, 17, 24},
//                        {18, 21, 23, 26, 30}},
//                20);
//        System.out.println(b);

        boolean b = solution.searchMatrix(new int[][]{
                {-5}
        }, -10);
    }
}
