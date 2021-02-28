package com.gson.algo.tree;

/**
 * https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 *
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 */
public class IsBalancedTree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    /**
     * 左，右子树高度差超过1时，返回-1;
     * 否则返回树的高度
     * @param node
     * @return
     */
    int depth(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftDepth = depth(node.left);
        if (leftDepth == -1){
            return -1;
        }

        int rightDepth = depth(node.right);
        if (rightDepth == -1){
            return -1;
        }
        if (Math.abs(leftDepth - rightDepth) > 1){
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
