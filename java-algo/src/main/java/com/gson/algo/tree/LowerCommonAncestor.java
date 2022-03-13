package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * https://www.nowcoder.com/practice/e0cc33a83afe4530bcec46eba3325116
 * 在二叉树中找到两个节点的最近公共祖先。
 * 节点值各不相同。
 *
 *                   3
 *            5               1
 *       6       2        0       8
 *            7     4
 */
public class LowerCommonAncestor {
    public int lowerCommonAncestor(TreeNode node, int o1, int o2){
        return helper(node, o1, o2).val;
    }

    /**
     * 递归函数，
     * @param node
     * @param o1
     * @param o2
     * @return 以node节点为根节点，返回o1,o2的最近公共祖先。
     * 如果o1,o2分别在其左右子树，则返回node，表示node为其最近的公共祖先。
     * 如果node.val ==o1,则返回node, 表示node就是o1,o2的最近公共祖先
     * 如果node.val ==o2,则返回node, 表示node就是o1,o2的最近公共祖先
     *
     * 如果o1,o2分别在其左子树，则返回node.left,  表示node.left可能为其最近的公共祖先，继续递归
     * 如果o1,o2分别在其右子树，则返回node.right, 表示node.right可能为其最近的公共祖先，继续递归
     *
     * 前序遍历
     */
    private TreeNode helper(TreeNode node, int o1, int o2){
        if (node == null){
            return null;
        }
        if (node.val == o1){
            return node;
        }
        if (node.val == o2){
            return node;
        }

        TreeNode left = helper(node.left, o1, o2);
        TreeNode right = helper(node.right, o1, o2);

        // 说明node的左子树遍历完也找不到一个节点等于o1或者o2
        // 说明o1,o2都在node的右子节点中
        if (left == null){
            return right;
        }

        // 说明o1,o2都在node的左子节点中
        if (right == null){
            return left;
        }

        //如果left和right都不为空，说明这两个节点一个在node的左子树上一个在node的右子树上，
        //我们只需要返回cur结点即可。
        return node;
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        LowerCommonAncestor lowerCommonAncestor = new LowerCommonAncestor();
        int i = lowerCommonAncestor.lowerCommonAncestor(node3, 6, 7);
        System.out.println(i);
    }
}
