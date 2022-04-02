package com.gson.algo.linklist;

import com.gson.algo.ListNode;

/**
 * https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {

    // 2 3 3 4 4 5
    // 2 3 3 4 4
    // 3 3 4 4 5
    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        ListNode before = new ListNode(pHead.value-1);
        before.next = pHead;
        ListNode cur = before;

        ListNode next;
        ListNode postNext;
        boolean exitSame = false;
        while (true) {
            next = cur.next;
            if (next == null){
                break;
            }

            postNext = next.next;
            if (postNext != null && next.value == postNext.value){
                //最后节点是重复节点
                if (postNext.next == null){
                    cur.next = null;
                    break;
                }
                cur.next = postNext;
                exitSame = true;
            }else if (postNext != null && next.value != postNext.value && exitSame){
                cur.next = postNext;
                exitSame = false;
            }else{
                cur = cur.next;
            }
        }

        return before.next;
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        ListNode before = new ListNode(pHead.value-1);
        before.next = pHead;
        ListNode pre = before;
        ListNode cur = pHead;

        while (true) {
            //cur与后面的节点相同
            if (cur.next != null && cur.next.value == cur.value){
                while (cur.next != null && cur.next.value == cur.value){
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;

            }
            //cur与随后的节点不相同
            else {
                pre = cur;
                cur = cur.next;
            }
            // 该条件可以放到while条件里
            if (cur == null){
                break;
            }
        }

        return before.next;
    }

    public static void main(String[] args) {
        DeleteDuplication deleteDuplication = new DeleteDuplication();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(5);
//        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;

        // 2 3 3 3 4 5 5 6
        ListNode node = deleteDuplication.deleteDuplication(node1);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
