package com.gson.algo.array;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class 布雷3 {
    private int M;
    private int N;
    int[][] directions = new int[][]{{1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}};

    // M雷 E未知
    public char[][] arrangeMineMap(int m, int n, int k){
        this.M = m;
        this.N = n;
        int total = m * n;
        char[][] map = new char[m][n];

        // index的后几个元素值就是地雷的一纬坐标
        int[] indexes = IntStream.range(0, total).toArray();
        Random random = new Random();
        int bound = total;
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(bound);
            bound--;
            swap(indexes, index, bound);
        }
        while (bound < total){
            int pos = indexes[bound];
            map[pos/n][pos%n] = 'M';
            bound++;
        }

        // 设置雷周围的格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '\u0000'){
                    map[i][j] = 'E';
                }else if (map[i][j] == 'M'){
                    setSurroundingMines(map, i, j);
                }
            }
        }
        return map;
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

    public void swap(int[] nums, int i, int j){
        if (i == j){
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        布雷3 布雷2 = new 布雷3();
        char[][] map = 布雷2.arrangeMineMap(10, 7, 20);
        for(char[] line : map){
            System.out.println(Arrays.toString(line));
        }
    }
}
