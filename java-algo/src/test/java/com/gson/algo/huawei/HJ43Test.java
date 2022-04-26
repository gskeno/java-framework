package com.gson.algo.huawei;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HJ43Test {
    @Test
    public void test(){
        int[][] table = new int[][]{
                new int[]{0, 1, 1, 1, 1},
                new int[]{0, 1, 1, 1, 1},
                new int[]{0, 0, 0, 0, 0},
                new int[]{0, 1, 1, 1, 0},
                new int[]{0, 0, 1, 1, 0},
        };
        List<HJ43迷宫问题.Point> pointList = new ArrayList<>();
        HJ43迷宫问题.helper(0, 0, table, pointList);
        for(HJ43迷宫问题.Point point: pointList){
            System.out.println("(" + point.x + "," + point.y + ")");
        }
    }

    @Test
    public void test1(){
        int[][] map = new int[][]{
                new int[]{0, 1, 1, 1, 1},
                new int[]{0, 1, 1, 1, 1},
                new int[]{0, 0, 0, 0, 0},
                new int[]{0, 1, 1, 1, 0},
                new int[]{0, 0, 1, 1, 0},
        };
        HJ43迷宫问题.bfs(map);
    }
}
