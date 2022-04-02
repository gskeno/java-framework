package com.gson.algo.linklist;

import com.gson.algo.ListNode;
import com.gson.algo.linklist.circle.CircleList;

/**
 * 将一个链表分为近相等(如果长度为奇数，则前一段长度比后一段长度更长)的前后两段，输出第二段的首节点。
 * 如 1->2->3->4,长度为4，第一段为1->2,第二段为3-4, 3为第二段的首节点
 * 如 1->2->3->4->5,长度为5，第一段为1->2->3,第二段为4-5, 4为第二段的首节点
 * 特别的，如果只有1个节点，则不存在第二段，第二段首节点为空
 *
 * 使用双指针
 */
public class FindHeadOfSecondSegment {

    public ListNode findHeadOfSecondSegment(ListNode node){
        ListNode slowPoint = node;
        ListNode quickPoint = node;

        // 快指针每次走两步，慢指针每次走一步
        while (quickPoint != null && quickPoint.next != null){
            quickPoint = quickPoint.next.next;
            slowPoint = slowPoint.next;
        }
        ListNode headOfSecondSegment = null;

        // 1-2->3->4->5->6这种
        if (quickPoint == null){
            headOfSecondSegment = slowPoint;
        }
        // 1->2->3->4->5这种
        else if (quickPoint.next == null){
            headOfSecondSegment = slowPoint.next;
        }

        return headOfSecondSegment;
    }

}
