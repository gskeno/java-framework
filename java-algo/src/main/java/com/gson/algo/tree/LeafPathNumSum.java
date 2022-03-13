package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * 从根节点到叶子节点的路径之和
 *            3
 *       9         0
 *   5      1    2
 *
 *   前序遍历，路径之和是 395 + 391 + 302
 */
public class LeafPathNumSum {

    /**
     * 每遍历到一个节点时，将当前节点表达的数字M计算出来，如果是叶子节点，直接返回；
     * 不是叶子节点，则继续处理孩子节点，并将M传进递归函数，重复如此
     * @param node
     * @return
     */
    public int leafPathNumSum(TreeNode node){
        int path = 0;
        int result = helper(node, path);
        return result;
    }

    public int helper(TreeNode node, int path){
        if (node == null){
            return 0;
        }
        path = path * 10 + node.val;
        // 已经到了叶子节点，则返回该节点表达的数字
        if (node.left == null && node.right == null){
            return path;
        }

        // 如果没到叶子节点，则继续处理孩子节点
        return  helper(node.left, path) + helper(node.right, path);
    }

    public static void main(String[] args) {
        LeafPathNumSum leafPathNumSum = new LeafPathNumSum();
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node0 = new TreeNode(0);
        node3.left = node9;
        node3.right = node0;

        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        node9.left = node5;
        node9.right = node1;

        TreeNode node2 = new TreeNode(2);
        node0.left = node2;


        int result = leafPathNumSum.leafPathNumSum(node3);
        System.out.println(result);
    }
}
