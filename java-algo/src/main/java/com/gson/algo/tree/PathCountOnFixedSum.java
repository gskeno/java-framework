package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * https://www.nowcoder.com/practice/965fef32cae14a17a8e86c76ffe3131f
 * 二叉树中和为某一值的路径(三)
 *
 * 给定一个二叉树root和一个整数值 sum ，求该树有多少路径的的节点值之和等于 sum 。
 * 1.该题路径定义不需要从根节点开始，也不需要在叶子节点结束，但是一定是从父亲节点往下到孩子节点
 * 2.总节点数目为n
 * 3.保证最后返回的路径个数在整形范围内(即路径个数小于231-1)
 */
public class PathCountOnFixedSum {
    private int count = 0;

    // 双重递归
    public int pathCountOnFixedSum (TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        // 选择node节点
        dfs(root, sum);
        // 不选择node节点
        pathCountOnFixedSum(root.left, sum);
        pathCountOnFixedSum(root.right, sum);
        return count;
    }

    // 选择node节点
    private void dfs(TreeNode node, int remaining){
        if (node == null){
            return;
        }
        // 并不阻断
        remaining = remaining - node.val;
        if (remaining == 0){
            count++;
        }
        dfs(node.left, remaining);
        dfs(node.right, remaining);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;

        TreeNode node44 = new TreeNode(4);
        TreeNode node33 = new TreeNode(3);
        node3.left = node44;
        node3.right = node33;

        TreeNode node_1 = new TreeNode(-1);
        node5.right = node_1;
        PathCountOnFixedSum pathCountOnFixedSum = new PathCountOnFixedSum();
        int result = pathCountOnFixedSum.pathCountOnFixedSum(node1, 6);
        System.out.println(result);

//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        node1.right = node2;
//        node2.right = node3;
//        node3.right = node4;
//        node4.right = node5;
//
//        PathCountOnFixedSum pathCountOnFixedSum = new PathCountOnFixedSum();
//        int result = pathCountOnFixedSum.pathCountOnFixedSum(node1, 3);
//        System.out.println(result);

    }
}
