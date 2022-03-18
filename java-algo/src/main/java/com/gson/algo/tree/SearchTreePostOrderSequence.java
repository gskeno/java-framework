package com.gson.algo.tree;

import java.util.Arrays;
import java.util.Stack;

/**
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果
 * https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd
 *        4
 *     2     5
 *  1     3     6
 */
public class SearchTreePostOrderSequence {
    /**
     * 第一种方法是二分法，这里先不讲
     * 主要看第二种方法，使用栈
     *
     * 中序遍历结果是 1->2->3->4->5->6
     * 后序遍历结果是 1->3->2->6->5->4
     * 一棵数，如果将中序遍历结果当作入栈序列，则后序遍历结果一定是其中一个出栈序列。
     * 如上, push1->pop1->push2->push3->pop3->pop2->push4->push5->push6->pop6->pop5->pop4
     * 上面push的值分别是1,2,3,4,5,6
     * pop的值分别是1,3,2,6,5,4
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST2(int [] sequence) {
        if (sequence.length == 0){
            return false;
        }
        int[] preOrders = Arrays.copyOf(sequence, sequence.length);
        Arrays.sort(preOrders);
        // 以中序结果入栈，以后序结果出栈，如果能走到中序遍历数组的末尾和后序遍历数组的末尾，就满足题目要求
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < preOrders.length && j < sequence.length; i++) {
            stack.push(preOrders[i]);

            // 后序数组当前元素要出栈
            while (!stack.isEmpty()  && stack.peek() == sequence[j]){
                stack.pop();
                j++;
            }
        }
        if (j == sequence.length){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SearchTreePostOrderSequence searchTreePostOrderSequence = new SearchTreePostOrderSequence();
        boolean b = searchTreePostOrderSequence.VerifySquenceOfBST2(new int[]{1, 3, 2});
        System.out.println(b);
    }
}
