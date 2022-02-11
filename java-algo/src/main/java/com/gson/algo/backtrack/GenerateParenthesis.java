package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成匹配的括号
 * 输入一个正整数n，请输出所有包含n个左括号和n个右括号的组合，要求
 * 每个组合的左括号和右括号匹配。
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n){
        List<String> result = new ArrayList<>();
        handle(result, "", n, n);
        return result;
    }

    public void handle(List<String> result, String parenthesis, int left, int right){
        // 左右括号都被使用完之后，则将该字符串添加到结果集中去
        if (left == 0 && right == 0){
            result.add(parenthesis);
            return;
        }

        // 只要还有左括号没被使用，就可以添加到字符串中
        if (left > 0){
            handle(result, parenthesis + "(", left -1, right);
        }

        // 只要现在已使用的左括号多于右括号，就可以追加一个右括号
        if (left < right){
            handle(result, parenthesis + ")", left , right -1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> stringList = generateParenthesis.generateParenthesis(4);
        System.out.println(stringList);
    }
}
