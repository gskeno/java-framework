package com.gson.algo.linklist.circle;

import com.gson.algo.ListNode;

/**
 * https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class CircleList {

    //方法1, 双指针，巧妙
    //初始化：快指针fast指向头结点， 慢指针slow指向头结点
    //让fast一次走两步， slow一次走一步，第一次相遇在C处，停止
    //然后让fast指向头结点，slow原地不动，让后fast，slow每次走一步，当再次相遇，就是入口结点。

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null){
            return null;
        }
        ListNode l = pHead;
        ListNode r = pHead;

        //l一次走两步，r一次走一步，直至相遇
        while (l != null && l.next != null && l.next.next != null){
            l = l.next.next;
            r = r.next;
            if (l == r){
                break;
            }
        }

        //跳出循环，1:相遇 2:节点到了末尾,没有环
        if (l == null || l.next == null || l.next.next == null){
            //没有环
            return null;
        }

        //存在环
        //r重新指向首节点，l，r每次走一步，再相遇时就是环的入口
        r = pHead;
        for (;;){
            if (r == l){
                return r;
            }
            r = r.next;
            l = l.next;
        }
    }

    //方法2， 判断链表是否存在环 -->计算环中节点的数目x --->双指针从表头开始走
    //(指针1先走x步，然后指针1和指针2一次走一步，直到指针1节点=指针2节点，得到环的入口)
}
