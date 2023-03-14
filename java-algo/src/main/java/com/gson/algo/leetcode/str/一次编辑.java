package com.gson.algo.leetcode.str;

/**
 *
 */
public class 一次编辑 {

    public boolean oneEditAway(String first, String second) {
        if (first.length() < second.length()) {
            // 保证第一个字符串长度 >= 第二个字符串长度
            return oneEditAway(second, first);
        }
        int m = first.length();
        int n = second.length();
        if (m - n >= 2) {
            return false;
        }
        int editTimes = 0;
        // 只能尝试替换0/1次字符
        if (m == n) {
            for (int i = 0; i < m && editTimes <= 1; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    editTimes++;
                }
            }
            return editTimes <= 1;
        }
        // 只能尝试删除1个字符
        for (int i = 0, j = 0; i < n && editTimes <= 1; i++, j++) {
            if (first.charAt(j) != second.charAt(i)) {
                editTimes++;
                i--;
            }
        }
        return editTimes <= 1;
    }


}
