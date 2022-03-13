package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的TreeNode结点。
 *
 * 核心思想:树的中序遍历
 */
public class SearchTree {

    private int index = 0;
    private TreeNode[] nodes;
    public TreeNode kthNode(TreeNode pRoot, int k) {
        if (k == 0){
            return  null;
        }
        index = 0;
        nodes = new TreeNode[k];
        midTravel(pRoot, k);
        return nodes[k-1];
    }

    private void midTravel(TreeNode node, int k){
        if(node != null){
            midTravel(node.left, k);
            if(index >= k){
                return;
            }
            nodes[index++] = node;
            midTravel(node.right, k);
        }
    }
}
