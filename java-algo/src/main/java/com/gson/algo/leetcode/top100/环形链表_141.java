package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class 环形链表_141 {

    /**
     * 快慢指针，快指针一次走两步，慢指针一次走一步，如果有环，则会相交。
     * 如果没环，则快指针最终会走到链表的末尾，没有next节点
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode quick = head;
        ListNode slow = head;
        while (quick != null && quick.next != null ){
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow){
                return true;
            }
        }
        return false;
    }

}
