package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/valid-parenthesis-string/
 * 给定一个只包含三种字符的字符串：（，）和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 * 任何左括号 (必须有相应的右括号 )。
 * 任何右括号 )必须有相应的左括号 (。
 * 左括号 ( 必须在对应的右括号之前 )。
 * *可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 *
 */
public class 有效的括号字符串 {

    /**
     * 1 从前往后遍历，左括号后方有足够多的右括号进行匹配
     * 2 从后往前遍历，右括号前方有足够多的左括号进行匹配
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        int leftBracketNums = 0;
        int rightBracketNums = 0;
        for (int i = 0; i < s.length(); i++) {
            // 贪心，将未知的 * 都当作左括号时，左括号还比明确的右括号少，说明无法匹配
            if (s.charAt(i) == '(' || s.charAt(i) == '*'){
                leftBracketNums++;
            }else {
                rightBracketNums++;
            }
            if (leftBracketNums < rightBracketNums){
                return false;
            }
        }

        leftBracketNums = 0;
        rightBracketNums = 0;
        for (int i = s.length() - 1; i >=0 ; i--) {
            // 贪心，将)和*都当作右括号，遍历过程中，如果左括号更多，说明无法匹配
            if (s.charAt(i) == ')' || s.charAt(i) == '*'){
                rightBracketNums++;
            }else {
                leftBracketNums++;
            }
            if ( leftBracketNums > rightBracketNums){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        有效的括号字符串 solution = new 有效的括号字符串();
        System.out.println(solution.checkValidString("()"));
        System.out.println(solution.checkValidString("(*)"));
        System.out.println(solution.checkValidString("(*))"));
    }
}
