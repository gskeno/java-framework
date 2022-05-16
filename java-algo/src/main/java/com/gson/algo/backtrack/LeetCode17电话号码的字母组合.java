package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetCode17
 * 键盘上2键上有字母abc，9键上有字母wxyz,
 * 输入一个数字形式的字符串，
 * 返回可能的字母组合
 * 如"23"
 * 返回["ad","ae",...,"cf"]
 */
public class LeetCode17电话号码的字母组合 {
    static char[][] keys = {
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
    };
    public List<List<String>> getCombination(String numStr) {
        List<List<Character>> cs = new ArrayList<>();
        for (char c : numStr.toCharArray()) {
            List<Character> list = new ArrayList<>();
            char[] chars = keys[c - '2'];
            for(char cc : chars){
                list.add(cc);
            }
            cs.add(list);
        }
        List<String> result = new ArrayList<>();
        List<Character> tempPath = new ArrayList<>();
        helper(result, cs, tempPath);
        return null;
    }

    /**
     * 想象一下，选择了数字 2,3,8,9
     * 递归方法
     * @param result 最终的所有路径集合
     * @param source 源数据电话号码对应的字母
     * @param tempPath 当前选择的路径
     */
    public void helper(List<String> result, List<List<Character>> source, List<Character> tempPath){
        // 表示所有数字都已选择完毕
        if (tempPath.size() == source.size()){
          result.add(String.valueOf(tempPath.toArray())) ;
          return;
        }

        // 选择一个字母
        List<Character> selectableElements = source.get(tempPath.size());
    }

    public static void main(String[] args) {
            new LeetCode17电话号码的字母组合().getCombination("29");
    }
}
