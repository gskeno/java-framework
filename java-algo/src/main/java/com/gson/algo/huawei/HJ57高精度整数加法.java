package com.gson.algo.huawei;

import java.util.Scanner;
import java.util.Stack;

public class HJ57高精度整数加法 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            System.out.println(getSum(str1, str2));
        }
    }

    public static String getSum(String str1, String str2){
        int index1 = str1.length()-1;
        int index2 = str2.length()-1;
        Stack<Character> stack = new Stack<>();
        //初始进位为0
        int carry = 0;
        while (index1 >= 0 && index2 >=0){
            carry = sum(str1.charAt(index1), str2.charAt(index2), carry, stack);
            index1--;
            index2--;
        }
        while (index1>=0){
            char c = str1.charAt(index1);
            int sum = (c- '0') + carry;
            stack.push((char)('0' + (sum % 10)));
            carry = sum/10;
            index1--;
        }

        while (index2>=0){
            char c = str2.charAt(index2);
            int sum = (c- '0') + carry;
            stack.push((char)('0' + (sum % 10)));
            carry = sum/10;
            index2--;
        }

        if (carry != 0){
            stack.push((char)('0' + carry));
        }
        String s = "";
        while (!stack.isEmpty()){
            s = s + stack.pop();
        }
        return s;
    }

    /**
     *
     * @param c1
     * @param c2
     * @param carry 比如当前在计算十位的相加，carry为个位到十位的进位
     * @return 当前进制位到下一进制位的carry
     */
    public static int sum(char c1, char c2, int carry, Stack<Character> stack){
        int sum = (c1 - '0') + (c2- '0') + carry;
        stack.push((char)('0' + (sum % 10)));
        return sum / 10;
    }
}
