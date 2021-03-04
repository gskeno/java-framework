package com.gson.algo.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class StackAsQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                Integer stackTop = stack1.pop();
                stack2.push(stackTop);
            }
            return stack2.pop();
        }else {
            return stack2.pop();
        }
    }

    //输入两个整数序列，第一个序列表示栈的压入顺序，
    // 请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
    // 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
    // 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        if (pushA.length != popA.length){
            return false;
        }
        int i = 0;
        int aL = pushA.length;
        int j = 0;
        for (; i < aL; i++) {
            stack.push(pushA[i]);
            if (pushA[i] == popA[j]){
                break;
            }
        }

        //全部push,都 != popA[0]
        if (stack.peek() != popA[j]){
            return false;
        }

        stack.pop();
        for(;;){
            if (stack.isEmpty()){
                if (i < aL -1){
                    stack.push(pushA[++i]);
                    continue;
                }
                break;
            }
            //再push
            if (stack.peek() != popA[j+1] && i<aL-1){
                stack.push(pushA[++i]);
            }
            //再pop
            else if (stack.peek() == popA[j+1]){
                stack.pop();
                j++;
            }else {
                break;
            }
        }
        if (stack.isEmpty() && i == aL-1 && j == popA.length-1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        StackAsQueue stackAsQueue = new StackAsQueue();
        boolean result =
                //stackAsQueue.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{6,1,2,3,4});
        //System.out.println(result);
        result = stackAsQueue.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1});
        System.out.println(result);

        result = stackAsQueue.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2});
        System.out.println(result);

        result = stackAsQueue.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{1,4,2,5,3});
        System.out.println(result);
    }
}
