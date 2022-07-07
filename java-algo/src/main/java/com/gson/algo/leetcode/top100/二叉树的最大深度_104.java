package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class 二叉树的最大深度_104 {

    /**
     * 递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
