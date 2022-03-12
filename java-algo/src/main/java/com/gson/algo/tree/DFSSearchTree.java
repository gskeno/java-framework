package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先遍历
 *         1
 *      2     3
 *   4    5 6    7
 *  8
 */
public class DFSSearchTree {

    /**
     * 中序遍历(递归)
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode node){
        List<Integer> result = new ArrayList<>();
        // 每个递归内容的执行动作 : 总是先遍历左子树，再遍历当前节点，再遍历右子树
        // 结束条件: 当前节点为空
        dfsInorder(result, node);
        return result;
    }

    private void dfsInorder(List<Integer> result, TreeNode node){
        if (node != null){
            dfsInorder(result, node.left);
            result.add(node.val);
            dfsInorder(result, node.right);
        }
    }

    /**
     * 中序遍历使用栈
     * @param node
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode node){
        TreeNode curNode = node;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        // 先按照左子节点指针找到最左子节点
        // 处理完该节点之后，按照中序遍历的原则，就要找到其右孩子，
        // 对右孩子进行上述同样的处理
        while (curNode != null || !stack.isEmpty()){
            while (curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }
            // ensure 到这里,curNode肯定为null
            // stack栈顶元素是最下层左子节点（当是完全二叉树时是这种情况，当不是完全二叉树时，可能不是如此)
            // 比如        1
            //      NIL        3
            // 此时stack的栈顶是1
            TreeNode cur = stack.pop();
            result.add(cur.val);
            //
            curNode = cur.getRight();
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;

        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node3.left = node6;
        node3.right = node7;

        TreeNode node8 = new TreeNode(8);
        node4.left = node8;

        DFSSearchTree dfsSearchTree = new DFSSearchTree();
        List<Integer> list = dfsSearchTree.inorderTraversal1(node1);
        System.out.println(list);

        List<Integer> list1 = dfsSearchTree.inorderTraversal2(node1);
        System.out.println(list1);
    }
}
