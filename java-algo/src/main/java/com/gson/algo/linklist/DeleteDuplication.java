package com.gson.algo.linklist;

/**
 * https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 2 3 3 4 4 5
    // 2 3 3 4 4
    // 3 3 4 4 5
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        ListNode before = new ListNode(pHead.val-1);
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
            if (postNext != null && next.val == postNext.val){
                //最后节点是重复节点
                if (postNext.next == null){
                    cur.next = null;
                    break;
                }
                cur.next = postNext;
                exitSame = true;
            }else if (postNext != null && next.val != postNext.val && exitSame){
                cur.next = postNext;
                exitSame = false;
            }else{
                cur = cur.next;
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
            System.out.println(node.val);
            node = node.next;
        }
    }
}
