package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class 翻转二叉树_226 {

    /**
     * 对于每个节点，自己不动，翻转自己的左孩子和右孩子
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = right;
        root.right = left;
        invertTree(left);
        invertTree(right);
        return root;
    }
}
