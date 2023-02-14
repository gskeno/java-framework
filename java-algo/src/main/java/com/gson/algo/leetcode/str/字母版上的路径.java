package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/alphabet-board-path/
 */
public class 字母版上的路径 {

    public String alphabetBoardPath(String target) {
        int curX = 0;
        int curY = 0;
        StringBuffer sb = new StringBuffer();
        for(char c : target.toCharArray()){
            // 当前字符的坐标
            int X = (c - 'a') / 5;
            int Y = (c - 'a') % 5;
            int deltaX = X - curX;
            int deltaY = Y - curY;
            if (c == 'z'){
                moveY(sb, deltaY);
                moveX(sb, deltaX);
            }else {
                moveX(sb, deltaX);
                moveY(sb, deltaY);
            }
            sb.append("!");
            curX = X;
            curY = Y;
        }
        return sb.toString();
    }

    private void moveX(StringBuffer sb, int deltaX){
        if (deltaX > 0){
            while(deltaX-- >0){
                sb.append("D");
            }
        }else if (deltaX < 0){
            while (deltaX++ < 0){
                sb.append("U");
            }
        }
    }

    private void moveY(StringBuffer sb, int deltaY){
        if (deltaY > 0){
            while (deltaY-- > 0){
                sb.append("R");
            }
        }else if (deltaY < 0){
            while (deltaY++ < 0){
                sb.append("L");
            }
        }
    }

    public static void main(String[] args) {
        字母版上的路径 solution = new 字母版上的路径();
        String ans;
        ans = solution.alphabetBoardPath("leet");
        System.out.println(ans);
        ans = solution.alphabetBoardPath("code");
        System.out.println(ans);
    }
}
