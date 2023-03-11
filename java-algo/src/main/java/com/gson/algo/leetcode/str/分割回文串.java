package com.gson.algo.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/palindrome-partitioning/
 */
public class 分割回文串 {

    /**
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        partition(ret, new ArrayList<>(), s);
        return ret;
    }

    /**
     * @param ret 最终结果
     * @param cur 当前选择
     * @param s   处理的字符串
     * @return
     */
    private void partition(List<List<String>> ret, List<String> cur, String s) {
        if (s.length() == 0) {
            ret.add(cur);
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (isHw(sub)){
                List<String> newCur = new ArrayList<>(cur);
                newCur.add(sub);
                partition(ret, newCur, s.substring(i));
            }
        }
    }

    private boolean isHw(String sub) {
        if (sub.length() == 1) {
            return true;
        }
        int i = 0;
        int j = sub.length() - 1;
        while (i <= j) {
            if (sub.charAt(i) == sub.charAt(j)){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        分割回文串 solution = new 分割回文串();
        System.out.println(solution.isHw("abcba"));

        List<List<String>> ret;
        ret = solution.partition("aab");
        System.out.println(ret);

        ret = solution.partition("a");
        System.out.println(ret);
    }

}
