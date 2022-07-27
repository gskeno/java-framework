package com.gson.algo.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 *
 * 中序遍历  左-->父-->右
 *
 */
public class 二叉树的中序遍历_94 {

    /**
     * 如何利用迭代算法中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        // 一直左孩子遍历入栈，直至到达叶子节点
        TreeNode node = root;
        while (node != null){
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()){
            // 遍历到该元素pop
            TreeNode pop = stack.pop();
            ans.add(pop.val);
            // 再遍历到pop的右孩子
            node = pop.right;
            while (node != null){
                stack.push(node);
                node = node.left;
            }
        }
        return ans;
    }
}
