package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * 二叉搜索树第K小的节点
 *            5
 *        3       7
 *    2     4   6    8
 *
 *
 */
public class KMinTree {
    public int KthNode (TreeNode proot, int k) {
        // write code here
        if(proot == null){
            return -1;
        }
        TreeNode target = new TreeNode(-1);
        int[] ele = {0};
        dfs(proot, target, ele, k);
        return target.val;
    }

    //中序遍历
    private void dfs(TreeNode node, TreeNode target, int[] ele, int k ){
        if(node != null){
            dfs(node.left, target, ele, k);
            ele[0]++;
            if(ele[0] == k){
                target.val = node.val;
                return;
            }
            dfs(node.right,target, ele, k);
        }
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        node5.left = node3;
        node5.right = node7;

        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        node3.left = node2;
        node3.right = node4;

        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        node7.left = node6;
        node7.right = node8;

        KMinTree kMinTree = new KMinTree();
        int kmin = kMinTree.KthNode(node5, 3);
        System.out.println(kmin);
    }
}
