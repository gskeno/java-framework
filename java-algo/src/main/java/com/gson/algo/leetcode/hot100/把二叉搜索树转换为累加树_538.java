package com.gson.algo.leetcode.hot100;

public class 把二叉搜索树转换为累加树_538 {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    public int dfs(TreeNode node, int base){
        // 递归结束
        if (node == null){
            return base;
        }
        // node是叶子节点
        if (node.left == null && node.right == null){
            node.val += base;
            return node.val;
        }

        // 先遍历右子树
        int maxOnRight = dfs(node.right, base);
        // 再处理本节点
        node.val  += maxOnRight;
        // 最后处理左子树
        return dfs(node.left, node.val);
    }

}
