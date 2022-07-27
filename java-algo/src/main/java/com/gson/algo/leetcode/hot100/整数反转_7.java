package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/reverse-integer/
 */
public class 整数反转_7 {

    public int reverse(int x) {
       return reverse4(x);
    }

    public int reverse2(int x) {
        boolean positive = x >= 0;
        x = positive ? x : -x;
        int y = 0;
        while (x != 0) {
            int mod = x % 10;
            x = x / 10;
            y = y * 10 + mod;
        }

        return positive ? y : -y;
    }

    public int reverse3(int x) {
        boolean positive = x >= 0;
        x = positive ? x : -x;
        int y = 0;
        while (x != 0) {
            if (positive && y > Integer.MAX_VALUE/10){
                return 0;
            }

            if (!positive && y < Integer.MIN_VALUE/10){
                return 0;
            }
            int mod = x % 10;
            x = x / 10;
            y = y * 10 + mod;
        }

        return positive ? y : -y;
    }

    public int reverse4(int x) {
        int y = 0;
        while (x != 0) {
            if (y > Integer.MAX_VALUE/10 || y < Integer.MIN_VALUE/10){
                return 0;
            }
            int mod = x % 10;
            x = x / 10;
            y = y * 10 + mod;
        }

        return y;
    }

    public int reverse5(int x) {
        boolean positive = x >= 0;
        x = positive ? x : -x;

        String strX = String.valueOf(x);
        // 非10位十进制数，反转后不会有溢出
        if (strX.length() < 10) {
            return positive ? Integer.valueOf(reverse(strX)) : -Integer.valueOf(reverse(strX));
        }

        // 十位十进制正数

        String reverse = reverse(strX);
        if (positive && reverse.compareTo("2147483647") > 0) {
            return 0;
        }
        // 十位十进制负数
        else if (!positive && reverse.compareTo("2147483648") > 0) {
            return 0;
        }
        return positive ? Integer.valueOf(reverse(strX)) : -Integer.valueOf(reverse(strX));
    }

    public String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        整数反转_7 solution = new 整数反转_7();
        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(120));
        System.out.println(solution.reverse(0));
        System.out.println(solution.reverse(1534236469));
        System.out.println(solution.reverse(534236469));
        // 21亿多，10位数
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(-7/10);
        System.out.println(-7%10);

    }
}
