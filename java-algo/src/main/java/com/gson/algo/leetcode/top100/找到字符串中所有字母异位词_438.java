package com.gson.algo.leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 */
public class 找到字符串中所有字母异位词_438 {

    public List<Integer> findAnagrams(String s, String p) {
        // 使用回溯方法，找出p的所有异位词,
        List<String> anagrams = new ArrayList<>();
        int len = p.length();
        boolean[] visited = new boolean[len];
        StringBuilder temp = new StringBuilder();
        getAllAnagrams(p, anagrams, temp, visited);

        List<Integer> posList = new ArrayList<>();
        // 从s的首位置开始遍历
        for (int i = 0; i < s.length(); i++) {
            if (i + len <= s.length() && anagrams.contains(s.substring(i, i+len))){
                posList.add(i);
            }
        }
        return posList;
    }

    public void getAllAnagrams(String p, List<String> ans, StringBuilder temp, boolean[] visited) {
        boolean allVisited = true;
        for (int i = 0; i < visited.length; i++) {
            // i未被选择
            if (visited[i] == false) {
                temp.append(p.charAt(i));
                visited[i] = true;
                getAllAnagrams(p, ans, temp, visited);
                // 恢复状态
                visited[i] = false;
                temp.deleteCharAt(temp.length() - 1);

                allVisited = false;
            }
        }

        if (allVisited) {
            ans.add(temp.toString());
        }
    }

    public static void main(String[] args) {
        找到字符串中所有字母异位词_438 solution = new 找到字符串中所有字母异位词_438();
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String p = "abcd";
        boolean[] visited = new boolean[p.length()];
        solution.getAllAnagrams(p, ans, sb, visited);

        System.out.println(ans);
    }
}
