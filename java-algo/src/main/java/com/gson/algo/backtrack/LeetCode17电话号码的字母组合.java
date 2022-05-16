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
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> getCombination(String numStr) {
        List<List<Character>> cs = new ArrayList<>();
        for (char c : numStr.toCharArray()) {
            List<Character> list = new ArrayList<>();
            char[] chars = keys[c - '2'];
            for (char cc : chars) {
                list.add(cc);
            }
            cs.add(list);
        }
        List<String> result = new ArrayList<>();
        List<Character> tempPath = new ArrayList<>();
        helper(result, cs, tempPath);
        return result;
    }

    /**
     * 想象一下，选择了数字 2,3,8,9
     * 递归方法
     *
     * @param result   最终的所有路径集合
     * @param source   源数据电话号码对应的字母
     * @param tempPath 当前选择的路径
     */
    public void helper(List<String> result, List<List<Character>> source, List<Character> tempPath) {
        // 表示所有数字都已选择完毕
        if (tempPath.size() == source.size()) {
            char[] chars = new char[source.size()];
            for (int i = 0; i < tempPath.size(); i++) {
                chars[i] = tempPath.get(i);
            }
            result.add(String.valueOf(chars));
            return;
        }

        // 选择一个字母,有很多路径可以选择
        List<Character> selectableElements = source.get(tempPath.size());
        for (char selectableEle : selectableElements) {
            // 作出选择，选择一个字母
            tempPath.add(selectableEle);
            // 递归
            helper(result, source, tempPath);
            // 回溯,恢复状态，回置到作出选择前的状态
            tempPath.remove(tempPath.size() - 1);

            // 上面三个步骤，我们叫做三部曲
        }
    }

    public static void main(String[] args) {
        List<String> combination = new LeetCode17电话号码的字母组合().getCombination("29");
        System.out.println(combination);
    }
}
