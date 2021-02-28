package com.gson.algo.tree;

/**
 * https://www.nowcoder.com/practice/a9d0ecbacef9410ca97463e4a5c83be7?tpId=13&tqId=11171&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 *
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * 比如：    源二叉树
 *             8
 *            /  \
 *           6   10
 *          / \  / \
 *         5  7 9 11
 *         镜像二叉树
 *             8
 *            /  \
 *           10   6
 *          / \  / \
 *         11 9 7  5
 */
public class MirrorTree {


    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode Mirror (TreeNode pRoot) {
        swap(pRoot);
        return pRoot;
    }

    void swap(TreeNode node){
        if (node != null){
            TreeNode temp = node.right;
            node.right = node.left;
            node.left = temp;
            swap(node.left);
            swap(node.right);
        }
    }
}

