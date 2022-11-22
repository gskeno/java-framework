package com.gson.algo.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/number-of-matching-subsequences/
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 *
 */
public class 匹配子序列的单词数 {

    // 记录每个字符出现的所有位置
    Map<Character, List<Integer>> letterPositions = new HashMap<>();
    public int numMatchingSubseq(String s, String[] words) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> positions = letterPositions.get(c);
            if (positions == null){
                positions = new ArrayList<>();
                positions.add(i);
                letterPositions.put(c, positions);
            }else {
                positions.add(i);
            }
        }
        int ans = 0;
        for(String word : words){
            if (isSubSeq(word)){
                ans++;
            }
        }
        return ans;
    }

    /**
     * word是否是s的子序列
     * @param word
     * @return
     */
    public boolean isSubSeq(String word){
        int minIndex = -1;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            List<Integer> positions = letterPositions.get(c);
            if (positions == null){
                return false;
            }
            // 尝试去找到原字符串s中的哪个位置的字符c匹配当前word中的字符c
            // 使用二分法，找到s中 第一个大于minIndex位置的元素
            minIndex = binarySearch(positions, minIndex);
            if (minIndex == -1){
                return false;
            }
        }
        return true;
    }

    /**
     * 返回大于target目标值的第一个元素。如果不存在，返回-1
     * @param positions
     * @param target
     * @return
     */
    public int binarySearch(List<Integer> positions, int target){
        int len = positions.size();
        int left = 0;
        int right = len - 1;
        int index = -1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (positions.get(mid) <= target){
                left = mid + 1;
            }else {
                index = mid;
                right = mid - 1;
            }
        }
        return index == -1 ? -1 : positions.get(index);
    }

    public static void main(String[] args) {
        匹配子序列的单词数 solution = new 匹配子序列的单词数();
        System.out.println(solution.binarySearch(Arrays.asList(1,3,5), 1));
        System.out.println(solution.binarySearch(Arrays.asList(1,3,5), 5));
        System.out.println(solution.binarySearch(Arrays.asList(1,3,5), 0));

        int ans = solution.numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace"});
        System.out.println(ans);
        ans = solution.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"});
        System.out.println(ans);
    }
}
