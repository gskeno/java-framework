package com.gson.algo.leetcode;

import com.gson.algo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/
 */
public class 二叉树的最大宽度 {

    public int widthOfBinaryTree(TreeNode root) {
        int maxWidth = 1;
        // 元素1是节点，元素2是层级，初始层级为0

        Queue<TreeNode> queue = new LinkedList<>();
        root.val = 1;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int firstNoOfCurLevel = -1;
            int lastNoOfCurLevel = -1;
            // 一次将一层的元素全部出队
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0){
                    firstNoOfCurLevel = node.val;
                }
                if (i == size -1){
                    lastNoOfCurLevel = node.val;
                }
                if (node.left != null){
                    node.left.val = node.val * 2;
                    queue.offer(node.left);
                }
                if (node.right != null){
                    node.right.val = node.val * 2 + 1;
                    queue.offer(node.right);
                }
            }
            maxWidth = Math.max(maxWidth, lastNoOfCurLevel - firstNoOfCurLevel + 1);
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        二叉树的最大宽度 solution = new 二叉树的最大宽度();
    }
}
