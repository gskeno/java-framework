package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 * <p>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class 二叉树展开为链表_114 {

    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        // 寻找前取节点
        while (cur != null) {
            TreeNode left = cur.left;
            // 寻找前驱节点
            if (left != null) {
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将前驱节点的右孩子指向 cur的右孩子
                pre.right = cur.right;
                // 将cur节点的右孩子指向cur节点的左孩子，将将cur节点左孩子置空
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }

    }


    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode pre = new TreeNode(0);
        preOrder(root, pre);
        root.left = null;
        root.right = pre.right.right;
    }

    public TreeNode preOrder(TreeNode node, TreeNode pre) {
        if (node == null) {
            return pre;
        }
        pre.right = new TreeNode(node.val);
        TreeNode pre1 = preOrder(node.left, pre.right);
        TreeNode pre2 = preOrder(node.right, pre1);
        return pre2;
    }

    public static void main(String[] args) {
        二叉树展开为链表_114 solution = new 二叉树展开为链表_114();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;

        solution.flatten(node1);
        System.out.println(node1);

    }
}
