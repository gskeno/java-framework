package com.gson.algo.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * <p>
 * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历，
 * inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder和inorder均 无重复 元素
 * inorder均出现在preorder
 * preorder保证 为二叉树的前序遍历序列
 * inorder保证 为二叉树的中序遍历序列
 * <p>
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 */
public class 从前序遍历和中序遍历构造二叉树_105 {
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, 0);
    }

    /**
     * 一个树的前序遍历为preoder数组 left0到right0
     * 一个树的中序遍历为inorder数组 left1到right1
     * 求该树
     *
     * @param preorder
     * @param left0
     * @param left1
     * @return preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     */
    public TreeNode buildTree(int[] preorder, int left0, int right0, int left1) {
        int rootValue = preorder[left0];
        TreeNode root = new TreeNode(rootValue);
        int k = map.get(rootValue);
        // 左子树元素个数
        int leftChildSize = k - left1;

        // 右子树元素个数
        int rightChildSize = right0 - left0 - leftChildSize;
        if (leftChildSize > 0) {
            TreeNode leftTree = buildTree(preorder, left0 + 1, left0 + leftChildSize, left1);
            root.left = leftTree;
        }
        if (rightChildSize > 0) {
            TreeNode rightTree = buildTree(preorder, left0 + leftChildSize + 1, right0, k + 1);
            root.right = rightTree;
        }
        return root;
    }

    public static void main(String[] args) {
        从前序遍历和中序遍历构造二叉树_105 solution = new 从前序遍历和中序遍历构造二叉树_105();
        TreeNode node = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(node);
        node = solution.buildTree(new int[]{-1}, new int[]{-1});
        System.out.println(node);
    }
}
