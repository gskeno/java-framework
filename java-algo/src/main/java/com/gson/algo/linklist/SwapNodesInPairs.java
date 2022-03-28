package com.gson.algo.linklist;

import com.gson.algo.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 *  给定 1->2->3->4, 你应该返回 2->1->4->3.
 *  https://blog.csdn.net/FaustoPatton/article/details/82993032
 */
public class SwapNodesInPairs {
    public static ListNode swapPairs1(ListNode head){
        //构造一个哑结点
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;

        while (pre.next != null && pre.next.next != null){
            ListNode second = pre.next.next;
            pre.next.next = second.next;
            second.next = pre.next;
            pre.next = second;
            pre = second.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        ListNode node1 = swapPairs1(node);

        while (node1!=null){
            System.out.println(node1.value);
            node1 = node1.next;
        }
    }
}
