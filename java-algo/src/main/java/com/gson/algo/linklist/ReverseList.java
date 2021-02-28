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

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     *
     * 思路:双指针
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        if (pHead == null ){
            return null;
        }
        //第一个指针先走k步，第二个指针不动
        //第一个指针每走一步，第二个指针也走一步，当第一个指针走到末尾时，
        //第二个指针的位置就是目标结点
        ListNode head1 = pHead;
        ListNode head2 = pHead;
        for (int i = 1; i <= k; i++) {
            if (head1 == null){
                return null;
            }
            head1 = head1.next;
        }
        while (head1 != null){
            head1 = head1.next;
            head2 = head2.next;
        }
        return head2;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

//        ListNode listNode = reverseList(listNode1);
//        System.out.println(listNode);
//        while (listNode.next != null){
//            System.out.println(listNode = listNode.next);
//        }

        System.out.println("--------");
        ReverseList reverseList = new ReverseList();
        ListNode listNode6 = reverseList.FindKthToTail(listNode1, 5);
        System.out.println(listNode6);

    }

}
