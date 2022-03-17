package com.gson.algo.dynamic.plan;

/**
 *
 * 矩阵中的路径
 * https://www.nowcoder.com/practice/2a49359695a544b8939c77358d29b7e6
 */
public class HasPath {

    public boolean hasPath (char[][] matrix, String word) {
        // 遍历二维数组
        int width  = matrix[0].length;
        int height = matrix.length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // 从i,j位置开始
                if (dfs(matrix, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] array, int i, int j, String word, int index){
        boolean res = false;
        // i, j 不在二维数组边界
        if ( i>= array.length || i <0 || j >= array[0].length || j < 0 ||
                index >= word.length() || array[i][j] != word.charAt(index)){
            return false;
        }
        if (index == word.length()-1){
            return true;
        }
        char temp = array[i][j];
        array[i][j] = ' ';
        res |= dfs(array, i, j+1, word, index+1)
                || dfs(array, i, j-1, word, index + 1)
                || dfs(array, i-1, j, word, index + 1)
                || dfs(array, i+1, j, word, index + 1);
        array[i][j] = temp;
        return res;
    }

    public static void main(String[] args) {
        char[] c1 = {'a','b','c','e'};
        char[] c2 = {'s','f','c','s'};
        char[] c3 = {'a','d','e','e'};

        char[][] matrix = {c1, c2, c3};
        String s = "abcced";
        HasPath hasPath = new HasPath();
        boolean b = hasPath.hasPath(matrix, s);
        System.out.println(b);
        s = "abcb";
        b = hasPath.hasPath(matrix, s);
        System.out.println(b);

        s = "see";
        b = hasPath.hasPath(matrix, s);
        System.out.println(b);
    }
}
