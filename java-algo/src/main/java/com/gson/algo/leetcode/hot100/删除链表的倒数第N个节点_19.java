package com.gson.algo.leetcode.hot100;

import com.gson.algo.leetcode.ListNode;

/**
 * 删除链表的倒数第 N 个结点
 * <p>
 * 链表中结点的数目为 sz
 * <p>
 * 1 <= sz <= 30
 * <p>
 * 0 <= Node.val <= 100
 * <p>
 * 1 <= n <= sz
 */
public class 删除链表的倒数第N个节点_19 {

    /**
     * 双指针
     * n>=1
     * <p>
     * 第一个指针从第(n+1)个节点开始走。
     * 第二个指针从第1个节点(首节点)开始走。
     * 当第一个指针走到最后一个元素时，假设第二个指针走到了nodeX,则nodeX的下一个节点就是要删除的节点。
     * <p>
     * 举例
     * 1->2->3->4->5->6, 删除倒数第3个节点
     * a:           4->5->6
     * b:  1->2->3
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
        }

        if (p1 == null) {
            return head.next;
        }

        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        // p2.next就是要删除的节点
        p2.next = p2.next.next;
        return first;
    }

    /**
     * 回溯递归总是那么难
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int traverse = traverse(head, n);
        // traverse表示从后往前遍历的次数
        if(traverse == n)
            return head.next;
        return head;
    }

    /**
     * 返回值表示遍历到node时，从后往前遍历的次数。
     * 比如 1,2,3,4,5,6
     * 遍历到6时，从后往前遍历的次数是1
     * 遍历到5时，从后往前遍历的次数是2
     * @param node
     * @param n
     * @return
     */
    private int traverse(ListNode node, int n) {
        if(node == null)
            return 0;
        int num = traverse(node.next, n);
        if(num == n)
            node.next = node.next.next;
        return num + 1;
    }

    public static void main(String[] args) {
        删除链表的倒数第N个节点_19 solution = new 删除链表的倒数第N个节点_19();
        ListNode node6 = new ListNode(6, null);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(solution.removeNthFromEnd1(node1, 4));

//        ListNode one = new ListNode(1, null);
//        System.out.println(solution.removeNthFromEnd(one, 1));
    }
}
