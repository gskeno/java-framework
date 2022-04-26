package com.gson.algo.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ48从单向链表中删除指定值的节点 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            if (str.equals("")) continue;
            String[] split = str.split(" ");
            //总共有多少个节点
            int n = Integer.parseInt(split[0]);
            //头结点
            ListNode head = new ListNode(Integer.parseInt(split[1]));
            for (int i = 1; i < n; i++) {
                int pre = Integer.parseInt(split[2*i+1]), next = Integer.parseInt(split[2*i]);
                //临时遍历链表
                ListNode temp = head;
                //找到插入的位置
                while (temp.val != pre)
                    temp = temp.next;
                ListNode node = new ListNode(next);
                node.next = temp.next;
                temp.next = node;
            }
            int del_number = Integer.parseInt(split[2*n]);
            StringBuilder result = new StringBuilder();
            ListNode temp = head;
            while (temp != null) {
                if (temp.val != del_number)
                    result.append(temp.val).append(" ");
                temp = temp.next;//删除
            }
            // 注意要求每个数后面都加空格
            System.out.println(result.toString());
        }
    }

    static class ListNode {
        ListNode next;
        int val;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
}
