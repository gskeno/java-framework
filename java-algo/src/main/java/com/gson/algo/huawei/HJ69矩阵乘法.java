package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/ebe941260f8c4210aa8c17e99cbc663b
 */
public class HJ69矩阵乘法 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            int[][] grid1 = new int[x][y];
            int[][] grid2 = new int[y][z];
            int[][] result = new int[x][z];
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    grid1[i][j] = scanner.nextInt();
                }
            }

            for(int i = 0; i < y; i++){
                for(int j = 0; j < z; j++){
                    grid2[i][j] = scanner.nextInt();
                }
            }

            // grid1的第i行 * grid2的第j列 = result[i][j]
            for(int i = 0; i<x; i++){
                for(int j = 0; j < z; j++){
                    int sum = 0 ;
                    for(int k = 0; k< y; k++){
                        // 总是grid1的第i行 * grid2的第j列
                        sum += grid1[i][k] * grid2[k][j];
                    }
                    result[i][j] = sum;
                }
            }

            for(int k =0 ; k < result.length; k++){
                for(int m=0; m <result[0].length; m++){
                    System.out.print(result[k][m]);
                    if(m != result[0].length -1){
                        System.out.print(" ");
                    }
                }
                if(k != result.length -1){
                    System.out.println();
                }
            }
        }
    }
}
