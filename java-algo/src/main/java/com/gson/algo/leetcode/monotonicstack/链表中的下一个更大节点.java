package com.gson.algo.leetcode.monotonicstack;

import com.gson.algo.leetcode.ListNode;

import java.util.*;

/**
 * https://leetcode.cn/problems/next-greater-node-in-linked-list/
 */
public class 链表中的下一个更大节点 {

    /**
     * 单调递减栈
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> numsList = new ArrayList<>();
        while (head != null){
            numsList.add(head.val);
            head = head.next;
        }

        Integer[] nums = numsList.toArray(new Integer[]{});
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                Integer idx = stack.pop();
                ans[idx] = nums[i];
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        链表中的下一个更大节点 solution = new 链表中的下一个更大节点();
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(7);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int[] ret = solution.nextLargerNodes(node1);
        System.out.println(Arrays.toString(ret));
    }
}
