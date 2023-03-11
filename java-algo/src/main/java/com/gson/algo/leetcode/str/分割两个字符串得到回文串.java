package com.gson.algo.leetcode.str;

public class 分割两个字符串得到回文串 {

    /**
     * 对于S = a +b(或者b+a)， 维护一个长度为n的滑动窗口, 窗口的左边界最开始在0位置处，
     * 最多在n位置处，在移动窗口的过程中，判断窗口外的元素 连接在一起，是否能形成一个回文串。
     * @param a 长度为n
     * @param b 长度为n
     * @return
     */
    public boolean checkPalindromeFormation(String a, String b) {
        int n = a.length();
        String s = a + b;
        int start = 0;
        int end = 2*n -1;
        while (s.charAt(start) == s.charAt(end)){
            start++;
            end--;
        }
        return false;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public boolean checkPalindromeFormation1(String a, String b) {
        if (isPalindrome(a) || isPalindrome(b)){
            return true;
        }
        int n = a.length();
        // a整个串 与b的空串连接尝试是否是回文串
        // a串末尾去1, b串末尾加1， 连接尝试是否是回文串
        // ...
        // 一直到a串为空， b整个串， 连接尝试是否是回文串
        String s  = a + b;
        for (int i = n-1; i > 0 ; i--) {
            String sub = s.substring(0, i) + s.substring(i + n);
            if (isPalindrome(sub)){
                return true;
            }
        }
        // b在前
        s = b + a;
        for (int i = n-1; i > 0 ; i--) {
            String sub = s.substring(0, i) + s.substring(i + n);
            if (isPalindrome(sub)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断s是否是回文串
     * @param s
     * @return
     */
    private boolean isPalindrome(String s){
       if (s.length() <= 1){
           return true;
       }
       int i = 0;
       int j = s.length() - 1;
       while ( i <= j && s.charAt(i) == s.charAt(j)){
           i++;
           j--;
       }
       return i > j;
    }

    public static void main(String[] args) {
        分割两个字符串得到回文串 solution = new 分割两个字符串得到回文串();
        System.out.println(solution.checkPalindromeFormation1("ulacfd", "jizalu"));
        System.out.println(solution.checkPalindromeFormation1("abdef", "fecab"));
        System.out.println(solution.checkPalindromeFormation1("x", "y"));
    }
}
