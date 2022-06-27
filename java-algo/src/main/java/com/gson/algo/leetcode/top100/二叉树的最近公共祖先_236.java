package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 */
public class 二叉树的最近公共祖先_236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor1(root, p, q);
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p){
            return p;
        }
        if (root == q){
            return q;
        }
        if (isOnLeftChildTree(root, p) && isOnLeftChildTree(root, q)){
            return lowestCommonAncestor1(root.left, p, q);
        }

        if (isOnRightChildTree(root, p) && isOnRightChildTree(root, q)){
            return lowestCommonAncestor1(root.right, p, q);
        }
        return root;
    }

    /**
     * 判断p是否在node的左子树上
     * @param node
     * @param p
     * @return
     */
    private boolean isOnLeftChildTree(TreeNode node, TreeNode p){
        if (node == null){
            return false;
        }
        if (node == p){
            return false;
        }
        return isChildren(node.left, p);
    }

    private boolean isOnRightChildTree(TreeNode node, TreeNode p){
        if (node == null){
            return false;
        }
        if (node == p){
            return false;
        }
        return isChildren(node.right, p);
    }

    /**
     * 判断p是否是node的子孙(node==p时，也算是其子孙)
     * @param node
     * @param p
     * @return
     */
    private boolean isChildren(TreeNode node, TreeNode p){
        if (node == null){
            return false;
        }
        if (node == p){
            return true;
        }

        return isChildren(node.left, p) || isChildren(node.right, p);
    }
    public static void main(String[] args) {

    }
}
