package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/symmetric-tree/
 *
 * https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb
 */
public class 对称二叉树_101 {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    /**
     * 两个子树A，B对称，需要A节点与B节点相等，且A的左子树与B的右子树对称，A的右子树与B的左子树对称
     * @param A
     * @param B
     * @return
     */
    public boolean isSymmetric(TreeNode A, TreeNode B){
        if (A == null && B == null ){
            return true;
        }

        if (A != null && B != null && A.val == B.val && isSymmetric(A.left, B.right) && isSymmetric(A.right, B.left)){
            return true;
        }

        return false;
    }
}
