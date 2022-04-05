package com.gson.algo.linklist;

import com.gson.algo.ListNode;

/**
 * 重排链表
 *
 * https://leetcode-cn.com/problems/reorder-list/
 * @date 2022/4/4
 *
 * https://leetcode-cn.com/problems/reorder-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-34/
 */
public class ReOrderList1 {
    public void reorderList(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }

        //reorderListHelper(head, len);
        reorderList(head, len);
    }

    /**
     * 定义法: 返回当前层的尾部节点的下一个节点。
     * 什么是当前层? 因为这是一个递归函数，当前层就是递归函数执行到的层。
     * 上一层(外一层)更靠近调用栈底部，
     * 下一层(内一层)更靠近调用栈顶部，递归结束条件就在这一层。
     *
     * 那么返回当前层的尾部节点的下一个节点是什么意思呢？
     * 举个例子
     * 1->2->3->4->5。
     * 每层处理两个元素，所以会有三层。
     * 处理1,5最外层的时候，返回值应该是5的下一个节点NULL
     * 处理2,4中间层的时候，返回值应该是4的下一个节点，即5
     * 处理3  最内层的时候，返回值应该是3的下一个节点，即4
     *
     * 因为出栈是从最内层开始出栈的，所以上面的描述需要反着看，再看函数内部的处理逻辑
     * @param head
     * @param len
     * @return
     */
    private ListNode reorderListHelper(ListNode head, int len) {
        // 最内层1个元素的时候，返回第1个元素的下一个节点，即外一层的尾部节点
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            System.out.println("outerTail=" + outTail.value);
            return outTail;
        }
        // 最内层2个元素的时候，返回第2个元素的下一个节点，即外一层的尾部节点
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            System.out.println("outerTail=" + outTail.value);
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        //中间链表的头结点
        ListNode subHead = head.next;
        head.next = tail;
        //上一层 head 对应的 tail
        ListNode outTail = tail.next;
        tail.next = subHead;
        System.out.println("outerTail=" + (outTail == null ? "null" : outTail.value));
        return outTail;
    }

    private ListNode reorderList(ListNode head,int cnt){
        if(cnt==1) {
            return head;
        }
        else if(cnt==2) {
            return head.next;
        }
        ListNode midTail= reorderList(head.next,cnt-2);
        ListNode headNext=head.next; // m

        ListNode post=midTail.next;
        midTail.next=post.next;

        head.next=post; // m
        post.next=headNext; // m 将3处m标记的代码连在一起看
        return midTail;
    }

    public static void main(String[] args) {
        ReOrderList1 reOrderList1 = new ReOrderList1();
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        reOrderList1.reorderList(node1);
        System.out.println(node1);
    }
}
