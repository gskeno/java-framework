package com.gson.algo.stack;

import java.util.Stack;
public class 合法出栈序列 {
    /**
     * 针对一个数组，其中的元素可以依次入栈，出栈时机不做要求，判断一个序列是否是其合法的出栈序列
     *
     * 比如数组[1,2,3],
     * 3,2,1序列是一个合法的出栈序列，1，2，3依次入栈之后，再依次出栈
     * 2,1,3序列也是一个合法的出栈序列，1入栈，2入栈，2出栈，1出栈，3入栈，3出栈
     * 3,1,2不是一个合法的出栈序列
     */
    /**
     * 是否是合法的出栈序列,nums中的元素各不相同，都在0-9之前，
     * @return
     */
    public static boolean legalPop(int[] nums, String str){
        char[] chars = str.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        /**
         * 将入栈元素依次push到栈中，每push一次，就与出栈序列的头部指针指向的字符相比较，如果相等，则出栈指针后移一位；
         * 再比较栈顶与头部指针指向的字符，直至栈为空 或者 栈顶 与头部指针指向的字符不相等。 再处理数组的下一个入栈元素
         */
        for(int n : nums){
            stack.push(n);
            while (!stack.isEmpty() && '0' + stack.peek() == chars[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
