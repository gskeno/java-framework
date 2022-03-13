package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *              1
 *       2           3
 *   4       5    #     6
 * #   #   7
 * eg: {1,2,3,4,5,#,6,#,#,7} 返回值 4
 */
public class BinaryTree {

    /**
     * 递归 动态规划
     * 大问题转化为子问题
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        TreeNode treeNode6 = new TreeNode(6);
        treeNode3.right = treeNode6;

        TreeNode treeNode7 = new TreeNode(7);
        treeNode5.left = treeNode7;

        BinaryTree binaryTree = new BinaryTree();
        int depth = binaryTree.TreeDepth(treeNode1);
        System.out.println(depth);


    }
}
