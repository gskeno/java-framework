package com.gson.algo.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/
 * <p>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class 括号生成_22 {

    public List<String> generateParenthesis(int n) {
        // left表示剩下的左括号数量
        int left = n;
        // right表示剩下的右括号数量
        int right = n;
        // 0. 当剩下的左右括号数量都为0时，一种选择产生
        // 1. 当剩下的左括号数量为0时，只能使用右括号
        // 2. 当剩下的左括号数量 == 剩下的右括号数量时，只能再使用左括号
        // 3. 当剩下的左括号数量 <= 剩下的右括号数量时，可以用左，也可以用右
        //
        // 4. 当剩下的左括号数量 > 剩下的右括号数量时，非法
        List<String> ans = new ArrayList<>();
        generateParenthesis(left, right, ans, new StringBuilder());
        return ans;
    }

    public void generateParenthesis(int left, int right, List<String> ans, StringBuilder temp) {
        if (left == 0 && right == 0) {
            ans.add(temp.toString());
        } else if (left == 0) {
            temp.append(")");
            right--;
            generateParenthesis(left, right, ans, temp);

            // 回溯
            right++;
            temp.deleteCharAt(temp.length() - 1);
        } else if (left == right) {
            temp.append("(");
            left--;
            generateParenthesis(left, right, ans, temp);

            // 回溯
            left++;
            temp.deleteCharAt(temp.length() - 1);
        }else {
            temp.append("(");
            left--;
            generateParenthesis(left, right, ans, temp);

            // 回溯
            left++;
            temp.deleteCharAt(temp.length() - 1);


            temp.append(")");
            right--;
            generateParenthesis(left, right, ans, temp);

            // 回溯
            right++;
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public static void main(String[] args) {
        括号生成_22 solution = new 括号生成_22();
        System.out.println(solution.generateParenthesis(1));
        System.out.println(solution.generateParenthesis(2));
        System.out.println(solution.generateParenthesis(3));
    }
}
