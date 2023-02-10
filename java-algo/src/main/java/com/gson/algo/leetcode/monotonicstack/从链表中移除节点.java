package com.gson.algo.leetcode.monotonicstack;

import com.gson.algo.leetcode.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/remove-nodes-from-linked-list/
 */
public class 从链表中移除节点 {

    private int max;

    public ListNode removeNodes1(ListNode head){
        if (head == null){
            return head;
        }
        ListNode listNode = removeNodes1(head.next);
        if (head.val < max){
            return listNode;
        }else {
            max = head.val;
            head.next = listNode;
            return head;
        }
    }

    /**
     * 下一个更大节点
     * @param head
     * @return
     */
    public ListNode removeNodes(ListNode head) {
        // 维护一个单调递减栈
        List<Integer> list = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null){
            list.add(tmp.val);
            tmp = tmp.next;
        }
        // 存储下标
        Stack<Integer> stack = new Stack<>();
        int[] nextLargerArr = new int[list.size()];
        Arrays.fill(nextLargerArr, -1);
        for (int i = 0; i < list.size() ; i++) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)){
                Integer top = stack.pop();
                nextLargerArr[top] = i;
            }
            stack.push(i);
        }

        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;
        for (int i = 0; i < list.size(); i++) {
            if (nextLargerArr[i] == -1){
                newHead.next = head;
                newHead = head;
            }
            head = head.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        从链表中移除节点 solution = new 从链表中移除节点();
        ListNode node0 = new ListNode(5);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(13);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(8);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode listNode = solution.removeNodes(node0);
        System.out.println(listNode);

        listNode = solution.removeNodes1(node0);
        System.out.println(listNode);
    }
}
