package com.gson.algo.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
 */
public class 数青蛙 {
    /**
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
//        // 遍历时，每个字符出现的次数
//        int c = 0;
//        int r = 0;
//        int o = 0;
//        int a = 0;
//        int k = 0;
//        // 此时正在叫的青蛙数量，不含已经叫完的
//        int ret = 0;
//        // 记录出现过的ret最大值
//        int ans = -1;
//        for (int i = 0; i < croakOfFrogs.length(); i++) {
//            char letter = croakOfFrogs.charAt(i);
//            if (letter == 'c') {
//                c++;
//                ret++;
//                ans = Math.max(ans, ret);
//            }
//            if (letter == 'r') {
//                r++;
//                // r出现的次数 比c出现的次数多时，中断
//                if (r > c) {
//                    return -1;
//                }
//            }
//
//            if (letter == 'o') {
//                o++;
//                if (o > r) {
//                    return -1;
//                }
//            }
//
//            if (letter == 'a') {
//                a++;
//                if (a > o) {
//                    return -1;
//                }
//            }
//
//            if (letter == 'k') {
//                k++;
//                if (k > a) {
//                    return -1;
//                }
//                ret--;
//            }
//        }
//        if (c != r || r != o || o != a || a != k) {
//            return -1;
//        }
//        return ans;
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        // 正在叫的青蛙数量最大值
        int ret = 0;
        for (char letter : croakOfFrogs.toCharArray()) {
            if (letter == 'c') {
                c++;
            }
            if (letter == 'r') {
                r++;
            }
            if (letter == 'o') {
                o++;
            }
            if (letter == 'a') {
                a++;
            }
            if (letter == 'k') {
                k++;
            }

            // c -k , 表示当前正在叫的青蛙数量
            ret = Math.max(ret, c - k);
            // 靠后的字母不能比靠前的字母，出现频次多
            if (r > c || o > r || a > o || k > a) {
                return -1;
            }
        }
        if (c == r && r == o && o == a && a == k){
            return ret;
        }
        return -1;
    }

    public static void main(String[] args) {
        数青蛙 solution = new 数青蛙();
        System.out.println(solution.minNumberOfFrogs("croakcroak"));
        System.out.println(solution.minNumberOfFrogs("crcoakroak"));
        System.out.println(solution.minNumberOfFrogs("croakcrook"));
    }

}
