package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22括号组成 {

    /**
     * 括号对数
     * @param n
     * @return
     */
    public List<String> bracketCombination(int n){
        List<String> result = new ArrayList<>();
        helper(result, n, 0, 0, new StringBuilder());
        return result;
    }

    public void helper(List<String> result, int n, int leftBracketNum, int rightBracketNum, StringBuilder sb){
        // 递归结束，左右括号都用完
        if (leftBracketNum == n && rightBracketNum == n){
            result.add(sb.toString());
            return;
        }
        // 左括号比右括号多，且左括号还没用完，则跟左右括号都可以
        if (leftBracketNum>rightBracketNum && leftBracketNum < n){
            // 选择左括号
            helper(result,n,leftBracketNum+1, rightBracketNum, sb.append("("));
            sb.deleteCharAt(sb.length()-1);

            // 选择右括号
            helper(result, n, leftBracketNum, rightBracketNum+1, sb.append(")"));
            sb.deleteCharAt(sb.length()-1);
        }
        // 左括号比右括号多，且左括号已经用完，则只能加右括号
        else if (leftBracketNum > rightBracketNum && leftBracketNum == n){
            // 选择右括号
            helper(result, n, leftBracketNum, rightBracketNum+1, sb.append(")"));
            sb.deleteCharAt(sb.length()-1);
        }
        // 左右括号一样多，则只能增加左括号
        else if (leftBracketNum == rightBracketNum){
            helper(result, n, leftBracketNum + 1, rightBracketNum, sb.append("("));
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        List<String> list;
        list = new LeetCode22括号组成().bracketCombination(1);
        System.out.println(list);

        list = new LeetCode22括号组成().bracketCombination(2);
        System.out.println(list);

        list = new LeetCode22括号组成().bracketCombination(3);
        System.out.println(list);
    }
}
