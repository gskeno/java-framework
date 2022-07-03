package com.gson.algo.leetcode.top100;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/min-stack/
 */
public class 最小栈_155 {
    private Stack<Integer> stack;
    private PriorityQueue<Integer> queue ;

    public 最小栈_155() {
        stack = new Stack<>();
        queue = new PriorityQueue<>();
    }

    public void push(int val) {
        stack.push(val);
        queue.offer(val);
    }

    public void pop() {
        Integer ele = stack.pop();
        queue.remove(ele);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return queue.peek();
    }

    public static void main(String[] args) {
        最小栈_155 solution = new 最小栈_155();
        solution.push(-2);
        solution.push(0);
        solution.push(-3);

        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.top());
        System.out.println(solution.getMin());

    }
}
