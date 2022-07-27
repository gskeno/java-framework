package com.gson.algo.leetcode.hot100;

import com.gson.algo.leetcode.ListNode;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 */
public class 两数相加_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        /**
         * 哑巴结点
         */
        ListNode dummyNode = new ListNode(0);
        ListNode pointer = dummyNode;
        while (l1 != null || l2 != null){
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            int result = val1 + val2 + carry;
            pointer.next = new ListNode(result % 10);
            carry = result / 10;
            pointer = pointer.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (carry != 0){
            pointer.next = new ListNode(carry);
        }
        return dummyNode.next;
    }
}
