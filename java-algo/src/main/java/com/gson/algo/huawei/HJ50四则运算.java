package com.gson.algo.huawei;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/9999764a61484d819056f807d2a91f1e
 *
 * 输入一个表达式（用字符串表示），求这个表达式的值。
 * 保证字符串中的有效字符包括[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’。且表达式一定合法。
 *
 * 输入：
 * 3+2*{1+2*[-4/(8-6)+7]}
 *
 * 输出：
 * 25
 */
public class HJ50四则运算 {

    /**
     * 将字符分为3类， 数字，括号，运算符。后面两种我这里统一称之为符号
     * 1. 如何识别是- 是减号还是负号
     *    如果 - 前面一个字符是数字，  则-表示减号，如 5*2-7，前面一个字符是2，是数字
     *    如果 - 前面一个字符是符号，  则-表示负号，如 2*-7,  2*[-3+2]，前面的字符分别是符号*和符号[
     *
     * 2. 所有的括号都被置为( 或者 ), 因为不同形式的括号优先级都是一致的，简化分析
     *
     * 3. 建立两个栈，一个栈A存储数字，一个栈B存储符号。
     *
     * 4. 定义一个函数f, 将数字栈A顶部的两个数字取出，符号栈B顶部的运算符取出，进行运算，运算结果push进A栈。
     *    注意临界情况，如果A中的数字个数不足两个，不用计算。
     *
     * 4. 入栈出栈的细节分析
     *    a. 每遍历到一个字符
     *       a.1 如果是数字，则push入数字栈
     *       a.2 如果是运算符
     *              a.2.1 B栈顶元素也是运算符，且比栈顶运算符优先级低或相等，则执行函数f,再入符号栈B 。如 5 + 2 - 3,
     *              a.2.2 B栈顶元素也是运算符，且比栈顶运算符优先级高，则将运算符入符号栈B
     *       a.3 如果是左括号，则直接入栈
     *       a.4 如果是右括号，则一直执行函数f，直至遇到符号栈B顶部元素是左括号停止，且将左括号弹栈
     *       a.5 如果-表示负号，则push 0 到数字栈A中，再将-负号push 到符号栈B中
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public static int operate(String str){
        char[] chars = str.toCharArray();
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> symbolStack = new Stack<>();
        char pre = ' ';
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // 数字直接入数字栈
            if (Character.isDigit(c)){
                int result = c - '0';
                while ( i + 1 < chars.length && Character.isDigit(chars[i+1])){
                    result = result * 10 + (chars[i+1] - '0');
                    i++;
                }
                numStack.push(result);
            }
            // - 表示负号，将后续的数字连起来，组成负数写进数字栈
            else if (c == '-' && (pre == ' ' || !Character.isDigit(pre))){
                int result = 0;
                while ( i + 1 < chars.length && Character.isDigit(chars[i+1])){
                    result = result * 10 + (chars[i+1] - '0');
                    i++;
                }
                numStack.push(-result);
            }
            // - 表示减号，因为前面是一个数字
            else if (c == '-' && Character.isDigit(pre)){
                if (lowerOrEqualPriority(c, symbolStack)){
                    f(numStack, symbolStack);
                }
                symbolStack.push(c);
            }
            // 左括号直接入栈
            else if (isLeftBracket(c)){
                symbolStack.push('(');
            }
            // 右括号则执行f函数,直到遇到一个左括号
            else if (isRightBracket(c)){
                while (true){
                    f(numStack, symbolStack);
                    if (!symbolStack.isEmpty() && symbolStack.peek() == '('){
                        symbolStack.pop();
                        break;
                    }
                }
            }
            // 运算符，比栈顶运算符优先级低或相等,减号不再这里执行
            else if (lowerOrEqualPriority(c, symbolStack)){
                f(numStack, symbolStack);
                symbolStack.push(c);
            }
            else {
                symbolStack.push(c);
            }
            pre = chars[i];
        }
        System.out.println(numStack);
        System.out.println(symbolStack);
        while (!symbolStack.isEmpty()){
            f(numStack, symbolStack);
        }
        return numStack.pop();
    }

    public static void f(Stack<Integer> stack1, Stack<Character> stack2){

        // 数字栈中元素不超过两个时，不用计算
        if (stack1.size() < 2){
            return;
        }
        if (stack1.isEmpty()){
            return;
        }
        int num1 = stack1.pop();
        if (stack1.isEmpty()){
            return;
        }
        int num2 = stack1.pop();
        Character c2 = stack2.pop();
        if (c2 == '-'){
            stack1.push(num2 - num1);
        }else if (c2 == '+'){
            stack1.push(num2 + num1);
        }else if (c2 == '*'){
            stack1.push(num2 * num1);
        }else if (c2 == '/'){
            stack1.push(num2 /num1);
        }
    }

    public static boolean isLeftBracket(char c){
        if (c == '{' || c == '[' || c == '('){
            return true;
        }
        return false;
    }

    public static boolean isRightBracket(char c){
        if (c == '}' || c == ']' || c == ')'){
            return true;
        }
        return false;
    }

    public static boolean lowerOrEqualPriority(char c1, Stack<Character> symbolStack){
        // 符号栈为空时，c1优先级高
        if (symbolStack.isEmpty()){
            return false;
        }

        Character peek = symbolStack.peek();

        // +,- 比 +，-，*，优先级低或者相等
        if ( (c1 == '+' || c1 == '-') && (peek != '(')){
            return true;
        }
        // *,/ 比 *,/相比，优先级一致
        if ( (c1 == '*' && c1 == '/') && (peek == '*' || peek == '/')){
            return true;
        }
        return false;
    }
}
