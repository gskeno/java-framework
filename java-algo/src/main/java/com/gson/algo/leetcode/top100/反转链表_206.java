package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class 反转链表_206 {

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
