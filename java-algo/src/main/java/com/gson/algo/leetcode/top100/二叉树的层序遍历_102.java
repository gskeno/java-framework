package com.gson.algo.leetcode.top100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */
public class 二叉树的层序遍历_102 {


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);


        while (!deque.isEmpty()){
            int size = deque.size();
            // 把当前层节点全部遍历完，且把下一层节点全部push进队列
            // 重点，每次遍历一层，而不是遍历一个节点
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pop();
                list.add(node.val);
                if (node.left != null){
                    deque.addLast(node.left);
                }
                if (node.right != null){
                    deque.addLast(node.right);
                }
            }
            ans.add(list);
        }

        return ans;
    }

    public static void main(String[] args) {
        二叉树的层序遍历_102 solution = new 二叉树的层序遍历_102();
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node3.left = node4;
        node3.right = node5;

        List<List<Integer>> lists = solution.levelOrder(node1);
        System.out.println(lists);
    }
}
