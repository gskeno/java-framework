package com.gson.algo.leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/lru-cache/
 * hashMap和双向链表
 * <p>
 * <p>
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache {

    static class Node {

        int key;
        int val;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;

    private int size;
    // 哑巴节点
    private Node head;
    // 哑巴节点
    private Node tail;

    // key是数字，value是双向链表节点
    private Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        delNode(node);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        // 节点不存在，构建节点，放到哈希表中，并移动到双向链表头部
        // 如果导致双向链表节点数量超出限制，删除尾部节点
        if (node == null) {
            Node goal = new Node(key, value);
            map.put(key, goal);

            moveToHead(goal);
            size++;
            if (size > capacity) {
                Node delNode = removeLast();
                map.remove(delNode.key);
            }
        }
        // 更新value，并将节点移动到首部(两个步骤，删除该位置节点，并移动到首部)
        else {
            node.val = value;
            delNode(node);
            moveToHead(node);
        }
    }

    public void delNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
    }

    public void moveToHead(Node node) {
        Node temp = head.next;
        head.next = node;
        node.next = temp;
        temp.pre = node;
        node.pre = head;
    }

    // 删除最后一个节点，并返回该节点
    public Node removeLast() {
        Node delNode = tail.pre;
        Node preNode = delNode.pre;
        delNode.pre = null;
        delNode.next = null;
        preNode.next = tail;
        tail.pre = preNode;
        return delNode;
    }
}
