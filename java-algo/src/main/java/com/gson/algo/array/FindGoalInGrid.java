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

    /**
     * 重在寻找规律, 以右上角数据为基准值base, 基准行号row为0, 基准列为col = array[0].length-1。
     * 如果target > base, 则行号下移, base也进行变更
     * 如果target < base, 则列号左移, base也进行变更
     * 如果target == base, 则返回true。
     *
     * 在 row < array.length且 col>=0的条件满足时重复以上过程。
     *
     * @param target
     * @param array
     * @return
     */
    public boolean find(int target, int[][] array) {
        int row = 0;
        int col = array[0].length-1;
        while (row < array.length && col >= 0){
            int base = array[row][col];

            if (target > base){
                row++;
            }
            if (target < base){
                col--;
            }
            if (target == base){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindGoalInGrid findGoalInGrid = new FindGoalInGrid();
        int[] line0 = {1, 2, 8, 9};
        int[] line1 = {2, 4, 9, 12};
        int[] line2 = {4, 7, 10, 13};
        int[] line3 = {6, 8, 11, 15};
        int[] line4 = {8, 10, 12, 17};
        int[] line5 = {10, 11, 13, 20};
        int[][] grid = {line0, line1, line2, line3, line4, line5};
        findGoalInGrid.find(7, grid);
    }
}
