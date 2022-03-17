package com.gson.algo.array;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class FindGoalInGridTest {

    private int[][] grid;

    @Before
    public void before(){
        Random random = new Random();
        int len = 1 + new Random().nextInt(100);
        grid = new int[len][len];
        grid[0][0] = new Random().nextInt(20);

        for (int i = 1; i < len; i++) {
            grid[0][i] = grid[0][i-1] + random.nextInt(5) + 1;
        }
        for (int i = 1; i < len; i++) {
            grid[i][0] = grid[i-1][0] + random.nextInt(5) + 1;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                grid[i][j] = Math.max(grid[i][j-1] , grid[i-1][j]) + random.nextInt(5) + 1;
            }
        }
    }

    @Test
    public void test(){
        int target = 107;
        FindGoalInGrid findGoalInGrid = new FindGoalInGrid();
        boolean result = findGoalInGrid.find(target, grid);
        System.out.println(result);
    }
}
