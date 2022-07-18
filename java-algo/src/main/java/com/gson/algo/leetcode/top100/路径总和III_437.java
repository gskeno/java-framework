package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/path-sum-iii/
 */
public class 路径总和III_437 {

    /**
     * 选择 根节点的路径总数 + 不选择根节点的路径总数
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 选择根节点
        int res1 = pathSum(root, true, targetSum);

        // 不选择根节点
        int res2 = pathSum(root, false, targetSum);

        return res1 + res2;
    }

    public int pathSum(TreeNode node, boolean mustSelectNode, int targetSum) {
        if (node == null) {
            return 0;
        }
        if (mustSelectNode) {
            boolean positiveBegin = targetSum >= 0;
            targetSum -= node.val;
            if (!positiveBegin && node.val >0 && targetSum > 0) {
                return 0;
            }
            if (targetSum == 0) {
                return 1 + pathSum(node.left, true, 0) + pathSum(node.right, true, 0);
            } else {
                return pathSum(node.left, true, targetSum) + pathSum(node.right, true, targetSum);
            }
        } else {
            return pathSum(node.left, true, targetSum)
                    + pathSum(node.left, false, targetSum)
                    + pathSum(node.right, true, targetSum)
                    + pathSum(node.right, false, targetSum);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1000000000);
        TreeNode node2 = new TreeNode(1000000000);
        TreeNode node3 = new TreeNode(294967296);
        TreeNode node4 = new TreeNode(1000000000);
        TreeNode node5 = new TreeNode(1000000000);
        TreeNode node6 = new TreeNode(1000000000);

        root.left = node2;
        node2.left = node3;
        node3.left = node4;
        node4.left = node5;
        node5.left = node6;

        路径总和III_437 solution = new 路径总和III_437();
        int ans = solution.pathSum(root, 0);
        System.out.println(ans);

        System.out.println(0 - 1000000000 - 1000000000 - 294967296);
        System.out.println(Integer.MIN_VALUE);

    }
}
