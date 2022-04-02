package com.gson.algo.linklist;

import com.gson.algo.ListNode;

/**
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 */
public class FindFirstCommonNode {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int len1 = findListLenth(pHead1);
        int len2 = findListLenth(pHead2);
        if(len1 > len2){
            pHead1 = walkStep(pHead1,len1 - len2);
        }else{
            pHead2 = walkStep(pHead2,len2 - len1);
        }
        while(pHead1 != null){
            if(pHead1 == pHead2) {
                return pHead1;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }

    int findListLenth(ListNode pHead1){
        if(pHead1 == null) {
            return 0;
        }
        int sum = 1;
        while((pHead1 = pHead1.next) != null) {
            sum++;
        }
        return sum;
    }

    ListNode walkStep(ListNode pHead1, int step){
        while(step-- > 0){
            pHead1 = pHead1.next;
        }
        return pHead1;
    }

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(7);
        ListNode nodeA3 = new ListNode(2);
        ListNode nodeA4 = new ListNode(8);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = nodeA4;


        ListNode nodeB1 = new ListNode(3);
        ListNode nodeB2 = new ListNode(9);
        ListNode nodeB3 = new ListNode(5);
        ListNode nodeB4 = new ListNode(2);
        ListNode nodeB5 = new ListNode(8);
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        nodeB3.next = nodeB4;
        nodeB4.next = nodeB5;

        FindFirstCommonNode findFirstCommonNode = new FindFirstCommonNode();
        ListNode commonNode = findFirstCommonNode.FindFirstCommonNode(nodeA1, nodeB1);
        System.out.println(commonNode);

    }

}
