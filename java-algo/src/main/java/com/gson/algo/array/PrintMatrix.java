package com.gson.algo.array;

import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/9b4c81a02cd34f76be2659fa0d54342a?tpId=13&tqId=11172&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 顺时针打印矩阵
 * 最好的实现方式
 * https://blog.nowcoder.net/n/40241c8ec50f4b3daaf2d07f18712222?f=comment
 */
public class PrintMatrix {

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;

        int beginLine = 0;
        int lastLine = height - 1;
        int beginCol = 0;
        int lastCol = width - 1;

        //循环次数?
        int loopTimes = Math.min(height, width) % 2 == 0 ?
                Math.min(height, width) / 2 : Math.min(height, width) / 2 + 1;

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int times = 0; times < loopTimes; times++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = beginLine; i <= lastLine; i++) {
                //第一行顺时针加到列表中
                if (i == beginLine) {
                    for (int j = beginCol; j <= lastCol; j++) {
                        list.add(matrix[i][j]);
                    }
                }

                //只剩一行
                if (beginLine == lastLine) {
                    return list;
                }

                //中间行最后一个元素加到列表中
                if (i > beginLine && i < lastLine) {
                    list.add(matrix[i][lastCol]);

                    //起始列
                    if (beginCol != lastCol) {
                        temp.add(matrix[i][beginCol]);
                    }
                }

                //最后一行逆时针加到列表中
                if (i == lastLine) {
                    for (int j = lastCol; j >= beginCol; j--) {
                        list.add(matrix[i][j]);
                    }
                }
            }

            //起始行加入到列表中
            for (int i = temp.size() - 1; i >= 0; i--) {
                list.add(temp.get(i));
            }

            beginLine++;
            lastLine--;
            beginCol++;
            lastCol--;
            //n行2列场景
            if (beginCol > lastCol) {
                return list;
            }

        }
        return list;
    }

    public static void main(String[] args) {
        // 5行1列
        int[][] matrix = new int[5][1];
        matrix[0][0] = 0;
        matrix[1][0] = 1;
        matrix[2][0] = 2;
        matrix[3][0] = 3;
        matrix[4][0] = 4;
        ArrayList<Integer> integers = new PrintMatrix().printMatrix(matrix);
        System.out.println(integers);

        //1行5列
        matrix = new int[1][5];
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 2;
        matrix[0][3] = 3;
        matrix[0][4] = 4;
        integers = new PrintMatrix().printMatrix(matrix);
        System.out.println(integers);

        //3行2列
        matrix = new int[3][2];
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[1][0] = 2;
        matrix[1][1] = 3;
        matrix[2][0] = 4;
        matrix[2][1] = 5;
        integers = new PrintMatrix().printMatrix(matrix);
        System.out.println(integers);

        //2行3列
        matrix = new int[2][3];
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 2;
        matrix[1][0] = 3;
        matrix[1][1] = 4;
        matrix[1][2] = 5;
        integers = new PrintMatrix().printMatrix(matrix);
        System.out.println(integers);

        //2行2列
        matrix = new int[2][2];
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[1][0] = 2;
        matrix[1][1] = 3;
        integers = new PrintMatrix().printMatrix(matrix);
        System.out.println(integers);


    }
}
