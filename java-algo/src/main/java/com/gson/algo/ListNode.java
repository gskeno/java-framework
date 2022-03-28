package com.gson.algo;

public class ListNode {
    public ListNode next;

    public int value;


    public ListNode(int value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "next=" + next +
                ", value=" + value +
                '}';
    }
}
