package com.gson.algo.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/additive-number/
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
 *
 * 给你一个只包含数字'0'-'9'的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 *
 * 说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现1, 2, 03 或者1, 02, 3的情况。
 */
public class 累加数 {
    /**
     * 提示1: 以 "199100199"距离, 1, 99, 100
     * 提示2: Long的最大值字符串长度为19
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        List<String[]> candidates = getAdditiveNumberStart(num);
        for(String[] candidate : candidates){
            if (isAdditiveNumber(num, candidate[0], candidate[1])){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断以first,second为起始元素，是否能构成累加器
     * @param num
     * @param first
     * @param second
     * @return
     */
    public boolean isAdditiveNumber(String num, String first, String second){
        int start = 0;
        while (true){
            if (start + first.length() + second.length() > num.length()){
                return false;
            }
            if (start + first.length() + second.length() == num.length()){
                return true;
            }
            long sum = Long.valueOf(first) + Long.valueOf(second);
            int end = start + first.length() + second.length() + (sum + "").length();
            if (end > num.length()){
                end = num.length();
            }
            if (num.substring(start + first.length() + second.length(), end).equals(sum + "")){
                start += first.length();
                first = second;
                second = sum + "";
            }else {
                return false;
            }
        }
    }

    /**
     * 找出nums中起始满足 m + n = k的m和n，有几个找几个
     * @param num
     * @return
     */
    public List<String[]> getAdditiveNumberStart(String num) {
        List<String[]> list = new ArrayList<String[]>();
        // 第一个数字以i位置结束(不包含i)
        for (int i = 1; i <= num.length() / 2 && i <19; i++) {
            // 第二个数字以j位置结束(不包含j)
            for (int j = i+1; j <= num.length() - num.length() /3 && (j-i) < 19 ; j++) {
                String one = num.substring(0, i);
                String two = num.substring(i, j);
                if (!legal(one) || !legal(two)){
                    continue;
                }
                long sum = Long.valueOf(one) + Long.valueOf(two);
                int len = (sum + "").length();
                String third = num.substring(j, j + len > num.length() ? num.length() : j +len);
                if ((sum + "").equals(third)){
                    list.add(new String[]{one, two});
                }
            }
        }
        return list;
    }
    private boolean legal(String s){
        if (s.startsWith("0") && s.length() > 1){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        累加数 solution = new 累加数();
        System.out.println(solution.isAdditiveNumber("1999999999999999910000000000000000"));
        System.out.println(solution.isAdditiveNumber("199111992"));
        System.out.println(solution.isAdditiveNumber("1023"));
        System.out.println(solution.isAdditiveNumber("112358"));
        System.out.println(solution.isAdditiveNumber("199100199"));
        System.out.println(solution.isAdditiveNumber("185"));
        System.out.println(solution.isAdditiveNumber("1234"));
        System.out.println(solution.isAdditiveNumber("1235"));
        System.out.println(solution.isAdditiveNumber("1235813"));
        System.out.println(solution.isAdditiveNumber("123581"));
    }
}
