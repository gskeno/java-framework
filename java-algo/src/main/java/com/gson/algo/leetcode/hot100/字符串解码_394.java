package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/decode-string/
 * <p>
 * 递归
 * <p>
 * 3[a2[c]]可转化为 a2[c]a2[c]a2[c]再转化为 accaccacc;
 * <p>
 * 即每遇到一个[ 和 ] 匹配对，记录下该匹配对的位置，重复[前的数字N次，直至字符串中不再有 [ 和 ]匹配对
 */
public class 字符串解码_394 {


    public String decodeString(String s) {
        return decodeString1(s);
    }

    public String decodeString1(String s) {
        int matchP1 = 0;
        int matchP2 = 0;
        boolean matched = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                matchP1 = i;
            } else if (s.charAt(i) == ']') {
                matchP2 = i;
            }

            if (matchP1 != 0 && matchP2 != 0) {
                matched = true;
                break;
            }
        }
        if (!matched) {
            return s;
        }
        StringBuilder sb = new StringBuilder();

        int digitEndIdx = matchP1 - 1;
        int digitBeginIdx = digitEndIdx;
        int idx = digitEndIdx -1 ;
        int cnt = 1;
        while (idx >= 0 && s.charAt(idx) >= '0' && s.charAt(idx) <= '9'){
            digitBeginIdx = idx;
            idx--;
            cnt++;
        }
        if (matchP1 - cnt >= 0) {
            // 不包含end位置值
            sb.append(s, 0, matchP1 - cnt);
        }
        int k = Integer.valueOf(s.substring(digitBeginIdx, digitEndIdx + 1));
        for (int i = 0; i < k; i++) {
            sb.append(s, matchP1 + 1, matchP2);
        }
        if (matchP2 + 1 <= s.length() - 1) {
            sb.append(s, matchP2 + 1, s.length());
        }
        return decodeString1(sb.toString());
    }

    public static void main(String[] args) {
        //System.out.println(new StringBuilder().append("abc", 0, 1));
        字符串解码_394 solution = new 字符串解码_394();
//        System.out.println(solution.decodeString1("3[a]2[bc]"));
//        System.out.println(solution.decodeString1("3[a2[c]]"));
//        System.out.println(solution.decodeString1("2[abc]3[cd]ef"));
//        System.out.println(solution.decodeString1("abc3[cd]xyz"));
        System.out.println(solution.decodeString1("100[leetcode]"));
    }
}
