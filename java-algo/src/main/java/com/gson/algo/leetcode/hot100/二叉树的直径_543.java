package com.gson.algo.leetcode.hot100;

public class 二叉树的直径_543 {

    /**
     * 左右子数的高度之和
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        // 经过当前节点
        int roads = getDepth(root.left) + 1 + getDepth(root.right) + 1;
        // 不经过当前节点，经过当前节点左节点
        int roadsLeft = diameterOfBinaryTree(root.left);
        int roadsRight = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(roads, roadsLeft), roadsRight);
    }

    /**
     * 以node为根节点的树高度，如果只有根节点，高度为0。
     * @param node
     * @return
     */
    public int getDepth(TreeNode node){
        if (node == null){
            return -1;
        }
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);
        return 1+ Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);

        root.left = left;
        root.right = right;
        二叉树的直径_543 solution = new 二叉树的直径_543();
        System.out.println(solution.getDepth(root));
        System.out.println(solution.getDepth(left));
        System.out.println(solution.getDepth(right));
        System.out.println(solution.diameterOfBinaryTree(root));
    }
}
