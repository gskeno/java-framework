package com.gson.algo.huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/15e41630514445719a942e004edc0a5b
 *
 */
public class HJ70矩阵乘法计算量估算 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            // n行2列，每行表示一个矩阵，第一列表示矩阵的行数，第二列表示矩阵的列数
            int[][] array= new int[n][2];
            for(int i=0; i < n; i++){
                int lines = scanner.nextInt();
                int cols = scanner.nextInt();
                array[i] = new int[]{lines, cols};
            }
            String rule = scanner.next();

            printComplexity(n, array, rule);
        }
    }

    public static void printComplexity(int n, int[][] array, String rule){
        int multiTimes = 0;
        Stack<Character> stack1 = new Stack();

        // (A(BC))
        for(char c : rule.toCharArray()){
            // 遇到左括号直接入栈
            if(c == '('){
                stack1.push(c);
            }
            // 每遇到一个右括号进行出栈
            else if( c==')'){
                // 将括号移除，只剩下括号内的字母
                char letterR= stack1.pop();
                char letterL = stack1.pop();
                int[] arr1 = array[letterL - 'A'];
                int[] arr2 = array[letterR - 'A'];
                multiTimes+= arr1[0] * arr1[1] * arr2[1];
                // 矩阵相乘的结果几行几列放到该位置
                array[letterL - 'A'] = new int[]{arr1[0], arr2[1]};
                // 弹出左括号
                stack1.pop();
                // 将左字母依然压入栈
                stack1.push(letterL);
            }
            // 遇到字母也直接入栈
            else {
                stack1.push(c);
            }
        }

        System.out.println(multiTimes);
    }
}
