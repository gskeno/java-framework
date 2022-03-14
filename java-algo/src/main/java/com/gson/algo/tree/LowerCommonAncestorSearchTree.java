package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * https://www.nowcoder.com/practice/d9820119321945f588ed6a26f0a6991f
 * 二叉搜索树的最近公共祖先
 */
public class LowerCommonAncestorSearchTree {
    public int lowerCommonAncestor(TreeNode node, int o1, int o2){
        // o1，o2 都比node小，则o1,o2的公共祖先在node的左子树中
        if (o1 < node.val && o2 < node.val){
            return lowerCommonAncestor(node.left, o1, o2);
        }
        // o1,o2 都比node大，则o1,o2的公共祖先在node的右子树中
        if (o1 > node.val && o2 > node.val){
            return lowerCommonAncestor(node.right, o1, o2);
        }

        return node.val;
    }
}
