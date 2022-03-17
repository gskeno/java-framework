package com.gson.algo.array;

/**
 * 二维数组中的查找
 * https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e
 * <p>
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * [8,10,12,17]
 * [10,11,13,20]
 * ]
 * 给定 target = 7，返回 true。
 * <p>
 * 给定 target = 3，返回 false。
 */
public class FindGoalInGrid {

    public static void main(String[] args) {
        int[] line0 = {0,1,2};
        int[] line1 = {1,2,3};
        int[] line2 = {2,3,4};
        int[][]grid = {line0, line1,line2};
        FindGoalInGrid findGoalInGrid = new FindGoalInGrid();
        boolean result = findGoalInGrid.find3(0, grid);
        System.out.println(result);
        result = findGoalInGrid.find3(1, grid);
        System.out.println(result);
        result = findGoalInGrid.find3(2, grid);
        System.out.println(result);
        result = findGoalInGrid.find3(3, grid);
        System.out.println(result);
        result = findGoalInGrid.find3(4, grid);
        System.out.println(result);
        result = findGoalInGrid.find3(5, grid);
        System.out.println(result);
    }

    /**
     * @param target
     *     0  1   2
     *     1  2   3
     *     2  3   4
     * @param grid   是一个长宽
     * @return
     */
    public boolean find3(int target, int[][] grid) {
        int beginIndex = 0;
        int endIndex = grid.length - 1;
        if (grid[endIndex][endIndex] < target){
            return false;
        }
        if (grid[0][0] > target ){
            return false;
        }

        while (beginIndex + 1< endIndex) {
            int midIndex = (beginIndex + endIndex) / 2;
            if (grid[midIndex][midIndex] == target){
                return true;
            }

            if (grid[midIndex][midIndex] < target) {
                beginIndex = midIndex;
            }

            if (grid[midIndex][midIndex] > target) {
                endIndex = midIndex;
            }


        }

        for (int i = beginIndex; i <= endIndex; i++) {
            for (int j = beginIndex; j <= endIndex; j++) {
                if (grid[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 含边界
     *
     * @param target
     * @param array
     * @param beginLine
     * @param endLine
     * @param beginCol
     * @param endCol
     * @return
     */
    public boolean find2(int target, int[][] array, int beginLine, int endLine, int beginCol, int endCol) {
        int lines = (endLine - beginLine) + 1;
        int columns = (endCol - beginCol) + 1;
        int minLen = Math.min(lines, columns);
        boolean high = lines > columns;
        boolean wide = columns > lines;

        // 正方行右下角等于目标值
        if (array[endLine][endCol] == target) {
            return true;
        }

        // 正方行右下角小于目标值
        if (array[endLine][endCol] < target) {
            return false;
        }
        // 正方行左上角等于目标值
        if (array[endLine - minLen][endCol - minLen] == target) {
            return true;
        }

        // 正方行左上角大于目标值
        if (array[endLine - minLen][endCol - minLen - 1] > target) {
            return find2(target, array, 0, high ? endLine - minLen - 1 : endLine, 0, high ? endCol : endCol - minLen - 1);
        }

        return find2(target, array, lines - minLen - 1, lines / 2, columns - minLen - 1, columns / 2)
                || find2(target, array, lines / 2, lines, columns / 2, columns);
    }


    public boolean find(int target, int[][] array) {
        int allLine = array.length;
        int allColumn = array[0].length;
        int lowerLine = 0;
        int highLine = allLine - 1;
        int leftCol = 0;
        int rightCol = allColumn - 1;

        while (array[lowerLine][0] < target && array[highLine][0] > target && lowerLine < highLine) {
            int midLine = (highLine + lowerLine) / 2;
            if (array[midLine][0] == target || array[midLine][allColumn - 1] == target) {
                return true;
            }
            if (array[midLine][0] < target && array[midLine][allColumn - 1] < target) {
                lowerLine = midLine + 1;
                continue;
            }

            if (array[midLine][0] < target && array[midLine][allColumn - 1] > target) {
                lowerLine = midLine;
                continue;
            }
            if (array[midLine][0] > target) {
                highLine = midLine - 1;
                continue;
            }

        }
        System.out.println("lowerLine=" + lowerLine + ",highLine=" + highLine);

        return false;
    }

//    public static void main(String[] args) {
//        FindGoalInGrid findGoalInGrid = new FindGoalInGrid();
//        int[] line0 = {1, 2, 8, 9};
//        int[] line1 = {2, 4, 9, 12};
//        int[] line2 = {4, 7, 10, 13};
//        int[] line3 = {6, 8, 11, 15};
//        int[] line4 = {8, 10, 12, 17};
//        int[] line5 = {10, 11, 13, 20};
//        int[][] grid = {line0, line1, line2, line3, line4, line5};
//        findGoalInGrid.find(7, grid);
//    }
}
