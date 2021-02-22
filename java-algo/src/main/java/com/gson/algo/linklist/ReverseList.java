package com.gson.algo.linklist;

/**
 * 链表反转
 */
public class ReverseList {

    static class ListNode{
        Integer value;
        ListNode next;

        public ListNode(Integer value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "value=" + value +
                    '}';
        }
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode pre = head;
        ListNode cur = head.next;

        if (cur != null){
            pre.next = null;
        }

        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode = reverseList(listNode1);
        System.out.println(listNode);
        while (listNode.next != null){
            System.out.println(listNode = listNode.next);
        }

    }

}
