package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/letter-case-permutation/
 */
public class 字母大小写全排序 {
    /**
     * 递归思想
     * @param s
     * @return
     */
    public List<String> letterCasePermutation(String s){
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(ans, sb, s);
        return ans;
    }

    public void dfs(List<String> ans, StringBuilder sb, String s){
        if (sb.length() == s.length()){
            ans.add(sb.toString());
            return;
        }
        int sbLen = sb.length();
        char c = s.charAt(sbLen);
        if (Character.isDigit(c)){
            sb.append(c);
            dfs(ans, sb, s);
            sb.deleteCharAt(sbLen);
        }else {
            sb.append(c);
            dfs(ans, sb, s);
            sb.deleteCharAt(sbLen);

            sb.append(Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c));
            dfs(ans, sb, s);
            sb.deleteCharAt(sbLen);
        }
    }

    public static void main(String[] args) {
        字母大小写全排序 字母大小写全排序 = new 字母大小写全排序();
        List<String> ans;
        ans = 字母大小写全排序.letterCasePermutation("A");
        System.out.println(ans);
        ans = 字母大小写全排序.letterCasePermutation("a1b2");
        System.out.println(ans);
    }
}
