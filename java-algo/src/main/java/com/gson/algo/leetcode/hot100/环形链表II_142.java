package com.gson.algo.leetcode.hot100;

import com.gson.algo.leetcode.ListNode;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 * 
 * 给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 * 
 * 
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 */
public class 环形链表II_142 {
    public ListNode detectCycle(ListNode head) {
        // 无环
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode quick = head;
        boolean encounter = false;
        while (quick != null && quick.next != null){
            slow = slow.next;
            quick = quick.next.next;
            if (slow == quick){
                encounter = true;
                break;
            }
        }

        if (!encounter){
            return null;
        }
        slow = head;
        while (slow != quick){
            slow = slow.next;
            quick = quick.next;
        }
        return slow;
    }
}
