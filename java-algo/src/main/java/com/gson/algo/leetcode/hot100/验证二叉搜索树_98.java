package com.gson.algo.leetcode.hot100;

public class 验证二叉搜索树_98 {

    public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 以node为根节点的树的所有节点都在lower和upper范围内(不含)
     * @param node
     * @param lower
     * @param upper
     * @return
     */
    public boolean isValidBST(TreeNode node, Long lower, Long upper){
        if (node.val <= lower || node.val >= upper){
            return false;
        }

        // 左子树都要比node.val小，修改判定的高边界
        if (node.left != null && !isValidBST(node.left, lower, (long)node.val)){
                return false;
        }
        // 右子树都要比node.val大，修改判定的低边界
        if (node.right != null && !isValidBST(node.right, (long)node.val, upper)){
            return false;
        }

        return true;
    }
}
