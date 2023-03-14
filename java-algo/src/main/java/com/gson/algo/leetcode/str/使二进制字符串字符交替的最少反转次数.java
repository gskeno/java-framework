package com.gson.algo.leetcode.str;

public class 使二进制字符串字符交替的最少反转次数 {

    /**
     * 滑动窗口 +  前缀和
     * 构建字符串 newS = s + s;
     * 滑动窗口，开始边界为 [0, len(s) - 1]， 左闭右闭。
     * 维持窗口大小不变，向右滑动，最终停止时，边界为 [len(s)-1, len(s)-1 + len(s)-1]
     * 记录每个窗口状态时，窗口内元素与 0101...或者1010...的匹配度，匹配度越高，修改的次数就越少
     * 
     * 且与0101....的匹配度 跟与 1010...的匹配度是相反的。就是说，
     * 跟0101...匹配了k个字符，那么与1010...就会匹配len(s) - k个字符
     * @param s
     * @return
     */
    public int minFlips(String s) {
        int len = s.length();
        s = s + s;
        int[] match = new int[s.length()];
        // 010101
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0 && s.charAt(i) == '0'){
                match[i] = 1;
            }else if (i % 2 == 1 && s.charAt(i) == '1'){
                match[i] = 1;
            }
        }
        // match原地求前缀和
        for (int i = 1; i < s.length(); i++) {
            match[i] += match[i-1];
        }
        int ans = len;
        for (int i = len; i < s.length(); i++) {
            ans = Math.min(ans, Math.min(match[i] - match[i-len], len - (match[i] - match[i-len])));
        }
        return ans;

    }

    public static void main(String[] args) {
        使二进制字符串字符交替的最少反转次数 solution = new 使二进制字符串字符交替的最少反转次数();
        System.out.println(solution.minFlips("111000"));
    }
}
