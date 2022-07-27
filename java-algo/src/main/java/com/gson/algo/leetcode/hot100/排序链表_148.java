package com.gson.algo.leetcode.hot100;

import com.gson.algo.leetcode.ListNode;

/**
 * https://leetcode.cn/problems/sort-list/
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 归并排序
 *
 */
public class 排序链表_148 {
    /**
     * 自底向上归并
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 无节点或者单节点，直接返回，不用排序
        if (head == null || head.next == null){
            return head;
        }
        // 计算列表长度
        int len = 0;
        ListNode p = head;
        while (p != null){
            len++;
            p = p.next;
        }
        //   元素 -1,   5,   3,   4,   0
        //   索引  0    1    2    3    4
        // 归并的长度逐步倍增，从1，到2到4到8，一直到>=链表长度
        // 初始化dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int subLen = 1; subLen < len; subLen<<=1) {
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while (cur != null){
                ListNode head1 = cur;
                for (int i = 1; i < subLen && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode head2 = cur.next;
                cur.next = null;
                cur = head2;
                for (int i = 1; i < subLen && cur != null ; i++) {
                    cur = cur.next;
                }
                ListNode next = null;
                if (cur != null){
                    next = cur.next;
                    cur.next = null;
                }
                ListNode mergeNode = merge(head1, head2);
                pre.next = mergeNode;
                while (pre.next != null){
                    pre = pre.next;
                }
                cur = next;
            }
        }
        return dummy.next;
    }

    public ListNode merge(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (head1 != null && head2 != null){
            if (head1.val < head2.val){
                p.next = head1;
                head1 = head1.next;
            }else {
                p.next = head2;
                head2 = head2.next;
            }
            p = p.next;
        }
        if (head1 != null){
            p.next = head1;
        }
        if (head2 != null){
            p.next = head2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        排序链表_148 solution = new 排序链表_148();
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        //ListNode node5 = new ListNode(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        //node4.next = node5;
        ListNode listNode = solution.sortList(node1);
        System.out.println(listNode);
    }
}
