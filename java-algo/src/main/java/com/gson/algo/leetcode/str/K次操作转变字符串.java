package com.gson.algo.leetcode.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1540. K 次操作转变字符串
 * 给你两个字符串s和t，你的目标是在 k次操作以内把字符串s转变成t。
 *
 * 在第 i次操作时（1 <= i <= k），你可以选择进行如下操作：
 *
 * 选择字符串 s中满足 1 <= j <= s.length且之前未被选过的任意下标 j（下标从 1 开始），并将此位置的字符切换 i次。
 * 不进行任何操作。
 * 切换 1 个字符的意思是用字母表中该字母的下一个字母替换它（字母表环状接起来，所以 'z'切换后会变成 'a'）。第 i次操作意味着该字符应切换i次
 *
 * 请记住任意一个下标 j最多只能被操作1 次。
 *
 * 如果在不超过 k次操作内可以把字符串 s转变成 t，那么请你返回true，否则请你返回false。
 *
 * 输入：s = "input", t = "ouput", k = 9
 * 输出：true
 * 解释：第 6 次操作时，我们将 'i' 切换 6 次得到 'o' 。第 7 次操作时，我们将 'n' 切换 7 次得到 'u' 。
 *
 */
public class K次操作转变字符串 {


    public boolean canConvertString1(String s, String t, int k) {
        if (s.length() != t.length()){
            return false;
        }
        // 需要key次变化的英文字母个数
        // 优化，map可以使用数组，容易知道key的个数最多有26个
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer differ = t.charAt(i) - s.charAt(i);
            if (differ < 0){
                differ += 26;
            }
            if (differ != 0){
                Integer changeLetterCount = map.getOrDefault(differ, 0);
                map.put(differ, changeLetterCount + 1);
                maxCount = Math.max(changeLetterCount * 26 + differ, maxCount);
                if (maxCount > k){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        K次操作转变字符串 solution = new K次操作转变字符串();
//        System.out.println(solution.canConvertString1("zzzzzzz", "aaaaaaa", 1000000000));

        System.out.println(solution.canConvertString1("iqssxdlb", "dyuqrwyr", 40));
        System.out.println(solution.canConvertString1("input", "ouput", 9));
        System.out.println(solution.canConvertString1("abc", "bcd", 10));
        System.out.println(solution.canConvertString1("aab", "bbb", 27));
    }


}
