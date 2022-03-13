package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * 节点值之和最大的路径
 *          -9
 *    4             20
 *              15       7
 *           -3
 * 由于路径可能只经过左子树或右子树而不经过根节点，为了求得二叉树的路径上节点值之和的最大值，
 * 需要先求出左右子树中路径节点值之和的最大值(左右子树中的路径不经过当前节点), 再求出经过根节点的路径
 * 节点值之和的最大值，最后对三者进行比较得到最大值。
 *
 * 由于先求出左右子树的路径节点值之和的最大值，再求根节点，这看起来就是后序遍历。
 */
public class MaxPathSum {
    public int maxPathSum(TreeNode root){
        int[] maxSum = {Integer.MIN_VALUE};
        dfs(root, maxSum);
        return maxSum[0];

    }

    /**
     *
     * @param node
     * @param maxSum maxSum[0]是什么？是包含(不包含)当前节点的路径节点值之和的最大值
     * @return 经过当前节点node，并前往其左子树或者右子树(二选一)的路径的节点值之和的最大值(含当前节点)
     */
    private int dfs(TreeNode node, int[] maxSum){
        if (node == null){
            return 0;
        }

        int[] maxSumLeft = {Integer.MIN_VALUE};
        // dfs的结果是经过左子树根节点node.left，设为A，并往A的左子树或者A的右子树的路径的节点值之和的最大值
        int left = Math.max(0, dfs(node.left, maxSumLeft));

        int[] maxSumRight = {Integer.MIN_VALUE};
        // dfs的结果是经过右子树根节点node.right，设为B，并往B的左子树或者B的右子树的路径的节点值之和的最大值
        int right = Math.max(0, dfs(node.right, maxSumRight));

        // node的左子树路径之和最大值 与 node的右子树路径之和最大值 中挑选最大的
        maxSum[0] = Math.max(maxSumLeft[0], maxSumRight[0]);
        // 再与 经过node节点并向左子树发展 + 右子树发展 的路径（形状 ^) 的节点值之和的最大值比较
        // 由于这里是后序遍历，还没触及到node的父节点呢！ 所以这里只右^这种情况
        maxSum[0] = Math.max(maxSum[0], node.val + left + right);
        System.out.println("maxSum[0]=" + maxSum[0]);

        return node.val + Math.max(left, right);

    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(-9);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(20);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node3.left = node4;
        node3.right = node5;

        TreeNode node6 = new TreeNode(-3);
        node4.left = node6;

        MaxPathSum maxPathSum = new MaxPathSum();
        int maxPath = maxPathSum.maxPathSum(node1);
        System.out.println(maxPath);
    }
}
