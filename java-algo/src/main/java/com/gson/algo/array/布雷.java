package com.gson.algo.array;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class 布雷 {

    /**
     *
     * @param M
     * @param N
     * @param K
     * @return -1 代表雷，-2 代表不是雷，-3表示不确定，0-8表示周边雷的个数
     */
    private  int M;
    private  int N;

    private int[][] directions = new int[][]{{1,0}, {1,-1}, {0,-1}, {-1,-1},
            {-1,0},{-1,1},{0,1},{1,1}};
    public int[][] emplaceMine(int M, int N, int K){
        this.M = M;
        this.N = N;
        int total = M * N;
        int[][] nums = new int[M][N];
        for(int[] num : nums){
            Arrays.fill(num, -3);
        }

        int[] serialNOS = IntStream.range(0, total).toArray();
        int border = total;
        for (int i = 0; i < K; i++) {
            Random random = new Random();
            int randomNo = random.nextInt(border);
            swap(serialNOS, randomNo, border - 1);
            border -=1;
        }

        // border下标到 total-1 都是地雷
        for(int i = border; i < total; i++){
            int pos = serialNOS[i];
            nums[pos/N] [pos % N] = -1;
        }
        setSurroundingMines(nums);
        return nums;
    }

    public void setSurroundingMines(int[][] nums){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // 雷
                if (nums[i][j] == -1){
                    continue;
                }
                int mines = 0;
                for(int[] direction : directions){
                    if (i + direction[1] < M && i + direction[1] >= 0
                            && j + direction[0] >= 0 && j + direction[0] < N
                            && nums[i+direction[1]][j+direction[0]] == -1
                    ){
                       mines++;
                    }
                }
                nums[i][j] = mines;
            }
        }
    }

    public static void swap(int[] nums, int i, int j){
        if (i == j){
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        布雷 布雷 = new 布雷();
        int[][] ints = 布雷.emplaceMine(3, 10, 5);
        for(int[] num : ints){
            System.out.println(Arrays.toString(num));
        }
    }
}
