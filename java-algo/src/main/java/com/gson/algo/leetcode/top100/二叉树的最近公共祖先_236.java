package com.gson.algo.leetcode.top100;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class 二叉树的最近公共祖先_236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor1(root, p, q);
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p) {
            return p;
        }
        if (root == q) {
            return q;
        }
        if (isOnLeftChildTree(root, p) && isOnLeftChildTree(root, q)) {
            return lowestCommonAncestor1(root.left, p, q);
        }

        if (isOnRightChildTree(root, p) && isOnRightChildTree(root, q)) {
            return lowestCommonAncestor1(root.right, p, q);
        }
        return root;
    }

    /**
     * 判断p是否在node的左子树上
     *
     * @param node
     * @param p
     * @return
     */
    private boolean isOnLeftChildTree(TreeNode node, TreeNode p) {
        if (node == null) {
            return false;
        }
        if (node == p) {
            return false;
        }
        return isChildren(node.left, p);
    }

    private boolean isOnRightChildTree(TreeNode node, TreeNode p) {
        if (node == null) {
            return false;
        }
        if (node == p) {
            return false;
        }
        return isChildren(node.right, p);
    }

    /**
     * 判断p是否是node的子孙(node==p时，也算是其子孙)
     *
     * @param node
     * @param p
     * @return
     */
    private boolean isChildren(TreeNode node, TreeNode p) {
        if (node == null) {
            return false;
        }
        if (node == p) {
            return true;
        }

        return isChildren(node.left, p) || isChildren(node.right, p);
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        findChild(root, p, stackP);

        Stack<TreeNode> stackQ = new Stack<>();
        findChild(root, q, stackQ);
        TreeNode lastSameTreeNode = root;
        while (!stackP.isEmpty() && !stackQ.isEmpty()){
            TreeNode popP = stackP.pop();
            TreeNode popQ = stackQ.pop();
            if (popP == popQ){
                lastSameTreeNode = popP;
            }
        }
        return lastSameTreeNode;
    }

    /**
     * 从parent向下递归寻找，是否可以遍历到child，如果可以，再跳出递归的过程中记录下child到parent的路径
     *
     * @param parent
     * @param child
     * @param stack
     * @return
     */
    public boolean findChild(TreeNode parent, TreeNode child, Stack<TreeNode> stack) {
        if (child == parent) {
            stack.push(child);
            return true;
        }
        if (parent == null) {
            return false;
        }
        if (findChild(parent.left, child, stack)) {
            stack.push(parent);
            return true;
        }
        if (findChild(parent.right, child, stack)) {
            stack.push(parent);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        二叉树的最近公共祖先_236 solution = new 二叉树的最近公共祖先_236();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        Stack<TreeNode> stack = new Stack<>();
        solution.findChild(node1, node4, stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
