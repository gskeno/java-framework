package com.gson.algo.leetcode.str;

public class 不含AAA或BBB的字符串 {
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public String strWithout3a3b(int a, int b) {
        int moreNum = a;
        int lessNum = b;
        char moreLetter = 'a';
        char lessLetter = 'b';
        if (a < b){
            moreNum = b;
            lessNum = a;
            moreLetter = 'b';
            lessLetter = 'a';
        }

        int remain = moreNum - lessNum;
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx++ < lessNum){
              if (remain-- > 0){
                  sb.append(moreLetter);
              }
              sb.append(moreLetter).append(lessLetter);
        }
        while (remain-- > 0){
            sb.append(moreLetter);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        不含AAA或BBB的字符串 solution = new 不含AAA或BBB的字符串();
        System.out.println(solution.strWithout3a3b(4,1));
        System.out.println(solution.strWithout3a3b(1,2));
        System.out.println(solution.strWithout3a3b(0,0));
        System.out.println(solution.strWithout3a3b(10,4));
    }
}
