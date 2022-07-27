package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 */
public class 合并两个有序链表_21 {

    /**
     * 设置哑巴节点
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        ListNode head1 = list1;
        ListNode head2 = list2;
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

        while (head1 != null){
            p.next = head1;
            head1 = head1.next;
            p = p.next;
        }

        while (head2 != null){
            p.next = head2;
            head2 = head2.next;
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        合并两个有序链表_21 solution = new 合并两个有序链表_21();

    }
}
