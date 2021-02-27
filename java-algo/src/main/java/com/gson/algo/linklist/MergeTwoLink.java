package com.gson.algo.linklist;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class MergeTwoLink {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode merge(ListNode list1,ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        ListNode newHead = new ListNode(0);
        ListNode head1 = list1;
        ListNode head2 = list2;
        ListNode curNode = newHead;
        while (head1 != null && head2 != null){
            if (head1.val <= head2.val){
                curNode.next = head1;
                curNode = curNode.next;
                head1 = head1.next;
            }else {
                curNode.next = head2;
                curNode = curNode.next;
                head2 = head2.next;
            }
        }

        if (head1 == null && head2 != null){
            curNode.next = head2;
        }
        if (head1 != null && head2 == null){
            curNode.next = head1;
        }

        return newHead.next;
    }
}
