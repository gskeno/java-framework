package com.gson.algo.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class 合并K个升序列表 {

    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        // 最小堆，堆顶元素“值”最小
        PriorityQueue<Object[]> queue = new PriorityQueue<>(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return ((ListNode)o1[0]).val - ((ListNode)o2[0]).val;
            }
        });
        for (int i = 0; i < k; i++) {
            ListNode listNode = lists[i];
            if (listNode != null){
                lists[i] = listNode.next;
                listNode.next = null;
                queue.offer(new Object[]{listNode, i});
            }
        }
        ListNode head = new ListNode();
        ListNode dummy = head;
        while (!queue.isEmpty()){
            Object[] poll = queue.poll();
            ListNode node = (ListNode)poll[0];
            head.next = node;
            head = head.next;
            int index = (int)poll[1];
            ListNode appendNode = lists[index];
            if (appendNode != null){
                lists[index] = appendNode.next;
                appendNode.next = null;
                queue.offer(new Object[]{appendNode, index});
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        // 最小堆，堆顶元素“值”最小
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {

            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for(ListNode node : lists){
            if (node != null){
                queue.offer(node);
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while (!queue.isEmpty()){
            ListNode node = queue.poll();
            tail.next = node;
            tail = tail.next;
            ListNode appendNode = node.next;
            if (appendNode != null){
                queue.offer(appendNode);
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        合并K个升序列表 solution = new 合并K个升序列表();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode7 = new ListNode(7);
        listNode1.next = listNode8;
        listNode2.next = listNode5;
        listNode5.next = listNode7;
        ListNode[] listNodes = new ListNode[]{listNode1, listNode2};
        ListNode listNode = solution.mergeKLists1(listNodes);
        System.out.println(listNode);
    }
}
