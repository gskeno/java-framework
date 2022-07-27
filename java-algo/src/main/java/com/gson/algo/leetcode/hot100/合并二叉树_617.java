package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/merge-two-binary-trees/
 */
public class 合并二叉树_617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return mergeTrees1(root1, root2);
    }

    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    /**
     *
     * @param node1
     * @param node2
     * @return node1和node2合并后的节点
     */
    public TreeNode dfs(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        if (node1 == null){
            return node2;
        }
        if (node2 == null){
            return node1;
        }
        TreeNode newNode= new TreeNode(node1.val + node2.val);
        newNode.left = dfs(node1.left, node2.left);
        newNode.right = dfs(node1.right, node2.right);
        return newNode;
    }

    public static void main(String[] args) {
        合并二叉树_617 solution = new 合并二叉树_617();
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode treeNode = solution.mergeTrees(root1, root2);
        System.out.println(treeNode);
    }

}
