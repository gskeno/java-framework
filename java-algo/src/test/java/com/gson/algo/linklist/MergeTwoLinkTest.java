package com.gson.algo.linklist;

import org.junit.Test;

public class MergeTwoLinkTest {
    @Test
    public void test(){
        MergeTwoLink mergeTwoLink = new MergeTwoLink();

        MergeTwoLink.ListNode listNode1 = new MergeTwoLink.ListNode(1);
        listNode1.next = new MergeTwoLink.ListNode(3);
        listNode1.next.next = new MergeTwoLink.ListNode(5);

        MergeTwoLink.ListNode listNode2 = new MergeTwoLink.ListNode(2);
        listNode2.next = new MergeTwoLink.ListNode(4);
        listNode2.next.next = new MergeTwoLink.ListNode(6);

        mergeTwoLink.merge(listNode1, listNode2);
    }
}
