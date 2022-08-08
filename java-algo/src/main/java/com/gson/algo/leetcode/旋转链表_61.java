package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/rotate-list/
 */
public class 旋转链表_61 {

    /**
     * 双指针
     * 注意点1: 如果k==链表的长度，则链表不发生变化，直接返回。
     * 否则，k%链表长度。比如取模之后为2，
     * 则原链表的最后两个节点放置到最前面，拼接起来就是答案
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 特殊情况
        if (k == 0 || head == null|| head.next == null){
            return head;
        }

        /**
         * pointer1先走k步
         */
        ListNode pointer1 = head;
        ListNode pointer2 = head;
        ListNode tail = null;
        int steps = 0;
        while (steps < k) {
            if (pointer1.next == null){
                tail = pointer1;
                break;
            }
            pointer1 = pointer1.next;
            steps++;
        }

        // 情况1，steps>0，比如k=5，链表长度为3，则走3步就到了链表末尾
        if (tail != null){
            int listLength = steps + 1;
            k = k % listLength;
            if (k == 0){
                return head;
            }
            for (int i = 1; i < listLength - k ; i++) {
                pointer2 = pointer2.next;
            }
            ListNode newHead = pointer2.next;
            pointer2.next = null;
            tail.next = head;
            return newHead;
        }
        // 情况2, steps==0，比如k=3，链表长度为5
        else {
            while (pointer1.next != null){
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
            }
            tail = pointer1;
            ListNode newHead = pointer2.next;
            pointer2.next = null;
            tail.next = head;
            return newHead;
        }
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test1() {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;

        旋转链表_61 solution = new 旋转链表_61();
        solution.rotateRight(node1, 4);
    }

    public static void test2(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        旋转链表_61 solution = new 旋转链表_61();
        solution.rotateRight(node1, 2);
    }

    public static void test3(){
        ListNode node1 = new ListNode(1);

        旋转链表_61 solution = new 旋转链表_61();
        ListNode listNode ;
//        listNode= solution.rotateRight(node1, 0);
//        System.out.println(listNode);
//        listNode = solution.rotateRight(node1, 1);
//        System.out.println(listNode);
        listNode = solution.rotateRight(node1, 2);
        System.out.println(listNode);
    }

    public static void test4(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;

        旋转链表_61 solution = new 旋转链表_61();
        solution.rotateRight(node1, 2);
    }
}
