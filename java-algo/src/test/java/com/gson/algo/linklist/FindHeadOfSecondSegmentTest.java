package com.gson.algo.linklist;

import com.gson.algo.ListNode;
import org.junit.Test;

public class FindHeadOfSecondSegmentTest {
    private FindHeadOfSecondSegment solution = new FindHeadOfSecondSegment();

    @Test
    public void test1(){
        ListNode node = new ListNode(1, null);
        ListNode headOfSecondSegment = solution.findHeadOfSecondSegment(node);
        System.out.println(headOfSecondSegment);
    }

    @Test
    public void test2(){
        ListNode node2 = new ListNode(2, null);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = node1;
        ListNode headOfSecondSegment = solution.findHeadOfSecondSegment(head);
        System.out.println(headOfSecondSegment);
    }

    @Test
    public void test3(){
        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = node1;
        ListNode headOfSecondSegment = solution.findHeadOfSecondSegment(head);
        System.out.println(headOfSecondSegment);
    }

    @Test
    public void test4(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = node1;
        ListNode headOfSecondSegment = solution.findHeadOfSecondSegment(head);
        System.out.println(headOfSecondSegment);
    }

}
