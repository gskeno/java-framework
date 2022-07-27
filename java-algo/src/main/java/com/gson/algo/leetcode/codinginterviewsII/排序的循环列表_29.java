package com.gson.algo.leetcode.codinginterviewsII;

import com.gson.algo.leetcode.Node;

/**
 * https://leetcode.cn/problems/4ueAj6/
 */
public class 排序的循环列表_29 {

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }

        // insertNode安插在哪里呢?
        // insertNode不小于前一个节点且不大于后一个节点；
        // 或者insertNode在最大节点走一步到最小节点的中间位置
        Node insertNode = new Node(insertVal);

        // 当前节点cur的下一个节点为next, 前一个节点为pre
        Node cur = head;
        Node next = cur.next;
        Node p1 = cur;
        while (p1.next != head){
            p1 = p1.next;
        }

        Node pre = p1;
        // 循环链表只有1个或者2个节点
        if (next == pre){
            if (insertVal >= cur.val && insertVal <= next.val){
                cur.next = insertNode;
                insertNode.next = next;
                return head;
            }else {
                next.next  = insertNode;
                insertNode.next = cur;
                return head;
            }
        }
        // 当cur不满足插入值在cur值与下节点值中间时，cur遍历到下一个节点
        while (true){
            // [3,5,1] , 6
            // [1,3,3] 4
            if (cur.val <= insertVal && cur.val > cur.next.val){
                break;
            }
            // [3,5,1] 0
            // [3,3,5] 0
            if (cur.val >= insertVal && cur.val > cur.next.val && insertVal < cur.next.val){
                break;
            }




            if (cur.val <= insertVal && cur.next.val >= insertVal){
                break;
            }
            // 走到了最大节点
            if (cur.next == head){
                break;
            }
            cur = cur.next;
        }
        // 已经满足条件
        insertNode.next = cur.next;
        cur.next = insertNode;
        return head;
    }


    public static void main(String[] args) {
        排序的循环列表_29 solution = new 排序的循环列表_29();
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        Node insert = solution.insert(node1, 6);
        //System.out.println(insert);
    }
}
