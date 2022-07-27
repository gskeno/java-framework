package com.gson.algo.leetcode.hot100;

import java.util.*;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 */
public class 找到字符串中所有字母异位词_438 {

    /**
     * 滑动窗口
     * 在s中维护一个长度为p的窗口，窗口内各个字母出现的数量与p中相同字母出现的数量完全一致，则当前滑动窗口符合要求
     * 窗口的左边界即是一个答案。
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> letterToCount = new HashMap<>();
        for (char c : p.toCharArray()) {
            letterToCount.put(c, letterToCount.getOrDefault(c, 0) + 1);
        }

        if (s.length() < p.length()) {
            return ans;
        }

        // 初始化窗口
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = p.length() - 1;
        for (int i = left; i <= right; i++) {
            window.put(s.charAt(i), window.getOrDefault(s.charAt(i), 0) + 1);
        }

        // 移动滑动窗口
        while (right < s.length()) {
            if (equal(window, letterToCount)) {
                ans.add(left);
            }
            left++;
            right++;
            if (window.get(s.charAt(left - 1)) - 1 == 0) {
                window.remove(s.charAt(left - 1));
            } else {
                window.put(s.charAt(left - 1), window.get(s.charAt(left - 1)) - 1);
            }
            if (right < s.length()) {
                window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
            }
        }
        return ans;
    }

    public boolean equal(Map<Character, Integer> c1, Map<Character, Integer> c2) {
        for (Map.Entry<Character, Integer> entry : c1.entrySet()) {
            if (!c2.containsKey(entry.getKey()) || !c2.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 超时
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams1(String s, String p) {
        // 使用回溯方法，找出p的所有异位词,
        Set<String> anagrams = new HashSet<>();
        int len = p.length();
        boolean[] visited = new boolean[len];
        StringBuilder temp = new StringBuilder();
        getAllAnagrams(p, anagrams, temp, visited);

        List<Integer> posList = new ArrayList<>();
        // 从s的首位置开始遍历
        for (int i = 0; i < s.length(); i++) {
            if (i + len <= s.length() && anagrams.contains(s.substring(i, i + len))) {
                posList.add(i);
            }
        }
        return posList;
    }

    public void getAllAnagrams(String p, Set<String> ans, StringBuilder temp, boolean[] visited) {
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
//        Set<String> ans = new HashSet<>();
//        StringBuilder sb = new StringBuilder();
//        String p = "aaaaaaaaaaaa";
//        boolean[] visited = new boolean[p.length()];
//        solution.getAllAnagrams(p, ans, sb, visited);
//
//        System.out.println(ans);

        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
//        System.out.println(solution.findAnagrams("abab","ab"));
//        System.out.println(solution.findAnagrams("aaaaaaaaaa","aaaaaaaaaaaaa"));
    }
}
