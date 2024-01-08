package com.gson.algo.array;

import java.util.Arrays;
import java.util.Random;

public class 布雷2 {
    private int M;
    private int N;
    int[][] directions = new int[][]{{1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}};

    // M雷 E未知
    public char[][] arrangeMineMap(int m, int n, int k){
        this.M = m;
        this.N = n;
        int total = m * n;
        char[][] mineMap = new char[m][n];
        // 雷设置在最后
        for (int i = total - k; i < total; i++) {
            mineMap[i/n][i % n] = 'M';
        }

        // 混乱，错误的实现
        Random random = new Random();
        for (int i = total; i >= total - k + 1 ; i--) {
            int index = random.nextInt(i);
            if (index == i - 1){
                continue;
            }
            char temp = mineMap[(i-1)/n][(i-1) % n];
            mineMap[(i-1)/n][(i-1) %n] = mineMap[index / n][index % n];
            mineMap[index/n][index % n] = temp;
        }

        // 设置雷周围的格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mineMap[i][j] == '\u0000'){
                    mineMap[i][j] = 'E';
                }else if (mineMap[i][j] == 'M'){
                    setSurroundingMines(mineMap, i, j);
                }
            }
        }
        return mineMap;
    }
    public void setSurroundingMines(char[][] mines, int i, int j){
        for(int[] direction : directions){
            int newR = i + direction[1];
            int newC = j + direction[0];
            if (newR < M && newR >= 0 && newC < N && newC >= 0 && mines[newR][newC] != 'M'){
                if (mines[newR][newC] > '0' && mines[newR][newC] < '9'){
                    mines[newR][newC] = (char)(mines[newR][newC] + 1);
                }else {
                    mines[newR][newC] = '1';
                }
            }
        }
    }

    public static void main(String[] args) {
        布雷2 布雷2 = new 布雷2();
        char[][] map = 布雷2.arrangeMineMap(10, 7, 20);
        for(char[] line : map){
            System.out.println(Arrays.toString(line));
        }
    }
}
