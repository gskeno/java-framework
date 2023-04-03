package com.gson.algo.leetcode.memoization;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://leetcode.cn/problems/EXvqDp/
 */
public class 弹珠游戏 {
    // 前进方向
    private int UP = 1;
    private int DOWN = 2;
    private int LEFT = 3;
    private int RIGHT = 4;
    // 顺时针方向
    int[] CLOCKWISE = {0, 4, 3, 1, 2};
    // 逆时针方向
    int[] ANTICLOCKWISE = {0, 3, 4, 2, 1};
    // 下一步，坐标变化量
    int[][] DELTA = new int[][]{{0,0}, {-1, 0}, {1,0}, {0, -1}, {0, 1}};

    //棋盘N行 * M列
    private char[][] plates;
    private int N;
    private int M;

    public int[][] ballGame(int num, String[] plate) {
        N = plate.length;
        M = plate[0].length();
        plates = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                plates[i][j] = plate[i].charAt(j);
            }
        }

        Set<String> ret = new HashSet<>();
        // 第一行垂直向下打入
        for (int j = 1; j < M - 1; j++) {
            if (plates[0][j] == '.' && hit(new int[]{0, j}, DOWN, num)){
                ret.add( 0 + "," + j);
            }
        }

        // 最后一行垂直向上打入
        for (int j = 1; j < M - 1; j++) {
            if (plates[N-1][j] == '.' && hit(new int[]{N-1, j}, UP, num)){
                ret.add( (N-1) + "," + j);
            }
        }

        // 最左一列垂直向右打入
        for (int i = 1; i < N-1; i++) {
            if (plates[i][0] == '.' && hit(new int[]{i, 0}, RIGHT, num)){
                ret.add(i + "," + 0);
            }
        }
        // 最后一列垂直向左打入
        for (int i = 1; i < N-1; i++) {
            if (plates[i][M-1] == '.' && hit(new int[]{i, M-1}, LEFT, num)){
                ret.add(i + "," + (M-1));
            }
        }
        int[][] ans = new int[ret.size()][2];
        int k = 0;
        Iterator<String> iterator = ret.iterator();
        while (iterator.hasNext()){
            String pos = iterator.next();
            String x = pos.substring(0, pos.lastIndexOf(","));
            String y = pos.substring(pos.lastIndexOf(",") + 1);
            ans[k++] = new  int[]{ Integer.valueOf(x), Integer.valueOf(y)};
        }
        return ans;
    }

    /**
     *
     * @param curPos 当前位置
     * @param preDirection 从什么方向push到当前位置的
     * @param steps 剩下的步数
     * @return 是否命中 弹珠洞
     */
    private boolean hit(int[] curPos, int preDirection, int steps){
        int line = curPos[0];
        int column = curPos[1];

        char c = plates[line][column];
        // 当前位置是弹珠洞
        if ( c == 'O'){
            return true;
        }

        // 当前位置不是弹珠洞
        // 步数用完
        if (steps == 0){
            return false;
        }

        // 下一步的方向
        int nextDirection = getNextDirection(preDirection, c);


        // 下边沿出界
        if (line == N - 1 && nextDirection == DOWN ){
            return false;
        }
        // 上边沿出界
        if (line == 0 && nextDirection == UP ){
            return false;
        }
        // 左边沿出界
        if (column == 0 && nextDirection == LEFT ){
            return false;
        }
        // 右边沿出界
        if (column == M - 1 && nextDirection == RIGHT){
            return false;
        }
        int nextLine = DELTA[nextDirection][0] + line;
        int nextColumn = DELTA[nextDirection][1] + column;
        int[] nextPos = new int[]{nextLine , nextColumn};
        return hit(nextPos, nextDirection, steps - 1);

    }

    /**
     *
     * @param preDirection 进入当前位置时的方向
     * @param c 当前位置的字符
     * @return 出当前位置时的方向
     */
    public int getNextDirection(int preDirection, char c){
        if (c == '.'){
            return preDirection;
        }
        if (c == 'E'){
            return CLOCKWISE[preDirection];
        }

        return ANTICLOCKWISE[preDirection];
    }

    public static void main(String[] args) {
        弹珠游戏 solution = new 弹珠游戏();
        int[][] ans = solution.ballGame(4, new String[]{"..E.", ".EOW", "..W."});
        ans = solution.ballGame(5, new String[]{".....","..E..",".WO..","....."} );
        ans = solution.ballGame(3, new String[]{".....","....O","....O","....."});
        System.out.println(Arrays.toString(ans));
    }


}
