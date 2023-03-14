package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/valid-tic-tac-toe-state/
 */
public class 有效的井字游戏 {

    public boolean validTicTacToe(String[] board) {
        int[][] m = new int[3][3];
        int oneCounts = 0;
        int negativeOneCounts = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X'){
                    m[i][j] = 1;
                    oneCounts++;
                } else if (board[i].charAt(j) == 'O') {
                    m[i][j] = -1;
                    negativeOneCounts++;
                }
            }
        }
        if (oneCounts - negativeOneCounts < 0 || oneCounts - negativeOneCounts > 1 ){
            return false;
        }
        int succeedX = 0;
        int succeedO = 0;
        for (int i = 0; i < 3; i++) {
            if (m[i][0] + m[i][1] + m[i][2] == 3){
                succeedX++;
            } else if (m[i][0] + m[i][1] + m[i][2] == -3) {
                succeedO++;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (m[0][j] + m[1][j] + m[2][j] == 3){
                succeedX++;
            }else if (m[0][j] + m[1][j] + m[2][j] == -3){
                succeedO++;
            }
        }
        if (m[0][0] + m[1][1] + m[2][2] == 3){
            succeedX++;
        } else if (m[0][0] + m[1][1] + m[2][2] == -3) {
            succeedO++;
        }

        if (m[0][2] + m[1][1] + m[2][0] == 3){
            succeedX++;
        }else if (m[0][2] + m[1][1] + m[2][0] == -3){
            succeedO++;
        }
        // 两方都没赢
        if (succeedX == 0 && succeedO == 0){
            return true;
        }
        // 两方都赢是不可能出现的
        if (succeedX > 0 && succeedO > 0){
            return false;
        }
        // 不可能有一方 形成3条线条
        if (Math.abs(succeedX - succeedO) > 2){
            return false;
        }

        // X形成线条，O未形成，且X出现次数=O出现次数，是不可能的
        if (succeedX == 1 && succeedO == 0 && oneCounts == negativeOneCounts){
            return false;
        }

        // O形成线条，X未形成，且X出现的次数> O出现的次数，是不可能de
        if (succeedO == 1 && succeedX == 0 && oneCounts > negativeOneCounts){
            return false;
        }
        // 有一方形成一条线条，另一方未形成线条
        if (Math.abs(succeedX - succeedO) == 1){
            return true;
        }

        // X组成两条线，则有5个X点
        if (succeedX == 2 && oneCounts == 5){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        有效的井字游戏 solution= new 有效的井字游戏();
        System.out.println(solution.validTicTacToe(new String[]{"XXX","XOO","OO "}));
    }
}
