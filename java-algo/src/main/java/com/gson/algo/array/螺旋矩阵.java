package com.gson.algo.array;

/**
 * 从中间开始，顺时针
 * 如
 *  21    22   23   24   25
 *  20    7    8     9   10
 *  19    6    1     2   11
 *  18    5    4     3   12
 *  17    16    15  14   13
 */
public class 螺旋矩阵 {

    public int[][] rotate(int n){
        int left   =  n/2 - 1;
        int right  =  n/2 + 1;
        int top    =  n/2 - 1;
        int bottom =  n/2 + 1;

        int i = n/2, j = n/2;
        int[][] matrix = new int[n][n];
        matrix[i][j] = 1;
        int count = 1;
        while (count <= n * n){
            j++;
            for (; j <= right; j++){
                matrix[i][j] = ++count;
            }
            if (count == n * n){
                break;
            }
            j--;
            if (right < n - 1){
                right++;
            }
            i++;
            for(; i <= bottom; i++){
                matrix[i][j] = ++ count;
            }
            i--;
            if (bottom < n - 1){
                bottom++;
            }
            j--;
            for(; j >= left; j--){
                matrix[i][j] = ++ count;
            }
            j++;
            if (left > 0){
                left--;
            }
            i--;
            for(; i >= top; i--){
                matrix[i][j] = ++ count;
            }
            i++;
            if (top > 0){
                top--;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        螺旋矩阵 螺旋矩阵 = new 螺旋矩阵();
        int[][] rotate = 螺旋矩阵.rotate(5);
        System.out.println(rotate);
    }
}
