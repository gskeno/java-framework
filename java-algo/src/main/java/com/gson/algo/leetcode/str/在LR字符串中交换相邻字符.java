package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/swap-adjacent-in-lr-string/
 */
public class 在LR字符串中交换相邻字符 {

    /**
     * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。
     * 一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。
     * 现给定起始字符串start和结束字符串end，请编写代码，
     * 当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
     *
     * 1. L可以向左移动，一次性可以跨过多个X，但不能跨过R,L 。就是交换后，相同编号的L位置可能提前
     * 2. R可以向右移动，一次性可以跨过多个X，但不能跨过R,L 。就是交换后，相同编号的R位置可能滞后
     * 3. L,R,X三类字母个数一样多。L,R的相对位置不会变。
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()){
            return false;
        }
        int n = start.length();
        int i = 0;
        int j = 0;
        for (; i < start.length(); i++,j++) {
            while (i < n && start.charAt(i) == 'X'){
                i++;
            }
            while (j < n && end.charAt(j) == 'X'){
                j++;
            }

            if ( i == n || j == n){
                return i == j;
            }

            // start和end中, LR的相对位置不能变
            if (start.charAt(i) != end.charAt(j)){
                return false;
            }
            // end中L位置不能滞后
            if (start.charAt(i) == 'L' && j > i){
                return false;
            }
            // end中R位置不能提前
            if (start.charAt(i) == 'R' && j < i){
                return false;
            }
        }
        while (j < end.length()){
            if (end.charAt(j) != 'X'){
                return false;
            }
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        在LR字符串中交换相邻字符 solution = new 在LR字符串中交换相邻字符();
        System.out.println(solution.canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}
