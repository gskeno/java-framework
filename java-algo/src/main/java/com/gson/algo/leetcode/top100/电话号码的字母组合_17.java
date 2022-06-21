package com.gson.algo.leetcode.top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class 电话号码的字母组合_17 {

    /**
     * 第一个{}表示匿名内部类，第二个{}表示匿名内部类的构造方法内部执行的代码逻辑
     */

    private static Map<Character, Character[]> phones = new HashMap<Character, Character[]>() {{
        put('2', new Character[]{'a', 'b', 'c'});
        put('3', new Character[]{'d', 'e', 'f'});
        put('4', new Character[]{'g', 'h', 'i'});
        put('5', new Character[]{'j', 'k', 'l'});
        put('6', new Character[]{'m', 'n', 'o'});
        put('7', new Character[]{'p', 'q', 'r', 's'});
        put('8', new Character[]{'t', 'u', 'v'});
        put('9', new Character[]{'w', 'x', 'y', 'z'});
    }};

    public List<String> letterCombinations(String digits) {

        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        dfs(digits, 0, sb, ans);
        return ans;
    }

    private void dfs(String digits, int index, StringBuilder sb, List<String> ans) {
        if (index == digits.length()) {
            ans.add(sb.toString());
            return;
        }


        Character[] letters = phones.get(digits.charAt(index));
        for (char letter : letters) {
            // 选择
            sb.append(letter);
            // 递归
            dfs(digits, index + 1, sb, ans);
            // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

        电话号码的字母组合_17 solution = new 电话号码的字母组合_17();
        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.letterCombinations("2"));
        System.out.println(solution.letterCombinations("23"));
    }
}
