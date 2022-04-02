package com.gson.algo.linklist;

import com.gson.algo.ListNode;
import org.junit.Test;

public class MergeTwoLinkTest {
    @Test
    public void test(){
        MergeTwoLink mergeTwoLink = new MergeTwoLink();

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(5);

        ListNode listNode2 = new ListNode(2);
        listNode2.next = new ListNode(4);
        listNode2.next.next = new ListNode(6);

        mergeTwoLink.merge(listNode1, listNode2);
    }
}
