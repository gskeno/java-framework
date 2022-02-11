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
        handle(result, n, n);
        return result;
    }

    public void handle(List<String> result, int left, int right){

    }
}
