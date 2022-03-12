package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * 二叉树剪枝 后序遍历。
 * 剪去二叉树中所有节点值全为0的子树
 *          1
 *      0        0
 *   0    0   0      1
 */
public class PruneTree {

    /**
     * 递归后序遍历，先到叶子层，如果当前节点的左右孩子节点都为空，且当前节点值为0，则当前节点可以剪去
     * @param node
     * @return 如果node节点需要被修剪，则返回null；否则，返回node本身
     */
    public TreeNode pruneTree(TreeNode node){
        if (node == null){
            return node;
        }

        TreeNode left = pruneTree(node.left);
        TreeNode right = pruneTree(node.right);
        // todo 这里要将left和right再次赋值给node的左右子结点, 不要忘了
        node.left = left;
        node.right = right;

        // 左右孩子都为空，且当前节点值为0，则当前节点被修饰
        if (left == null && right == null && node.val == 0){
            return null;
        }
        return node;
    }



}
