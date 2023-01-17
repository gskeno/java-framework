package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 * 面试题67. 把字符串转换成整数
 */
public class 把字符串转化成整数 {

    public int strToInt(String str) {
        int leadingPos = -1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' '){
                continue;
            }
            // 合法的起始字符
            if (ch == '+' || ch == '-' || Character.isDigit(ch)){
                leadingPos = i;
                break;
            }
            // ch是不合法的起始字符，比如是字母
            return 0;
        }
        // 全是空字符
        if (leadingPos == -1){
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(str.charAt(leadingPos));
        for (int i = leadingPos + 1; i <str.length() ; i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)){
                sb.append(ch);
            }else {
                break;
            }
        }
        String s = sb.toString();
        if (s.equals("+") || s.equals("-")){
            return 0;
        }

        if (s.startsWith("-")){
            s = sb.substring(1);
            if (beyondIntBorder(s, (Integer.MIN_VALUE + "").substring(1))){
                return Integer.MIN_VALUE;
            }else {
                return - Integer.valueOf(s);
            }
        }

        if (s.startsWith("+")){
           s =  s.substring(1);
        }

        if (beyondIntBorder(s, Integer.MAX_VALUE + "")){
            return Integer.MAX_VALUE;
        }else {
            return Integer.valueOf(s);
        }
    }

    private boolean beyondIntBorder(String s, String border){
        //移除前导0
        int idx = 0;
        while (idx < s.length() && s.charAt(idx) == '0'){
            idx++;
        }
        if (idx == s.length()){
            return false;
        }
        String str = s.substring(idx);
        if (str.length() > 10){
            return true;
        }else if (str.length() < 10){
            return false;
        }else {
            // 相等也算超过
            return str.compareTo(border) >= 0;
        }
    }

    public static void main(String[] args) {
        把字符串转化成整数 solution = new 把字符串转化成整数();
        System.out.println(solution.strToInt("-91283472332"));
        System.out.println(solution.strToInt("words and 987"));
        System.out.println(solution.strToInt("4193 with words"));
        System.out.println(solution.strToInt("42"));
        System.out.println(solution.strToInt("   -42"));
        System.out.println(solution.strToInt("   -042"));
        System.out.println(solution.strToInt("   +042"));
        System.out.println(solution.strToInt("   ++042"));
        System.out.println(solution.strToInt("-2147483648"));
    }
}
