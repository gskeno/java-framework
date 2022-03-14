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
     *
     * <br>
     * 另一种理解, 关键在于找到节点node下，节点o1，o2 最近公共祖先节点B的特征，且B与node的关系，方法返回值就是B
     *
     * 1. 如果node就等于o1,则o2就在node(o1)的左子树或者右子树中, node就是o1,o2的最近公共祖先，直接返回，结束递归。
     *    node等于o2时也同理。
     * 2. 如果node既不等于o1, 也不等于o2。则o1,o2在node1的左右子树中，至于怎么分布的，不清楚，需要分别对node的左右
     *    子树进行递归分析。
     *
     *
     *
     */
    private TreeNode helper(TreeNode node, int o1, int o2){
        if (node == null){
            return node;
        }
        if (node.val == o1){
            return node;
        }
        if (node.val == o2){
            return node;
        }

        TreeNode left = helper(node.left, o1, o2);
        TreeNode right = helper(node.right, o1, o2);

        // 左子树中o1, o2 都没有遍历到，则o1,o2都在右子树中，右子树中先遍历到的o1(或者o2)就是o1,o2的最近祖先
        if (left == null){
            return right;
        }

        // 右子树中o1,o2都没有遍历到，则o1,o2都在左子树中，左子树中最先遍历到的o1(o2)就是o1,o2的最近祖先
        if (right == null){
            return left;
        }

        //如果left和right都不为空，说明这两个节点一个在node的左子树上一个在node的右子树上，
        //当前节点就是o1,o2的最近祖先
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
