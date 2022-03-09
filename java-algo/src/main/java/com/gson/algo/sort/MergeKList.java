package com.gson.algo.sort;

import com.gson.algo.ListNode;

import java.util.PriorityQueue;

/**
 * K个已经排序好的链表，进行排序，生成一个全新的全局排序链表
 */
public class MergeKList {

    public ListNode mergeKLists(ListNode[] listNodes){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 最小堆
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2)-> n1.getValue() - n2.getValue());

        // 先将各个链表的首结点put进堆里去
        for(ListNode listNode : listNodes){
            minHeap.offer(listNode);
        }

        while (!minHeap.isEmpty()){
            // 取出堆顶最小节点
            ListNode head = minHeap.poll();
            cur.setNext(head);
            cur = head;

            if (head.getNext() != null){
                minHeap.offer(head.getNext());
            }
        }
        return dummy.getNext();
    }
}
