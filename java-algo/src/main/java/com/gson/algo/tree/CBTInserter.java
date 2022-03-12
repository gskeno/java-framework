package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *       1                1
 *    2     3   变     2     3
 * 4    5  6       4     5  6   7
 *
 * 变
 *              1
 *        2          3
 *    4      5    6      7
 *  8
 *
 *  树的广度优先遍历
 */
public class CBTInserter {
    private Queue<TreeNode> queue = new LinkedList<>();
    private TreeNode root;

    /**
     * 构造函数初始化
     * @param root
     */
    public CBTInserter(TreeNode root){
        this.root = root;
        // 广度优先遍历树，遍历结束后，树叶子上一层左右孩子不全健在的节点(可能不存在这样的节点，比如满二叉树)
        // 和叶子层的节点从左向右，从上向下保存在队列中
        queue.add(root);
        while (queue.peek().left != null && queue.peek().right != null){
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        // 如上述例子，queue队首的元素是3，因为3是第一个左右孩子不全健在的节点，
        // 队列中的元素共有 3，4，5，6
    }

    /**
     * 插入新元素，返回新元素的父节点
     * @return
     */
    public int insert(int v){
        TreeNode treeNode = new TreeNode(v);
        // 第一个孩子不健全的节点如果连左孩子都没有，则直接添加新节点作为左孩子，队列不动
        if (queue.peek().left == null){
            queue.peek().left = treeNode;
            queue.offer(treeNode);
            return queue.peek().getVal();
        }
        // 第一个孩子不健全的节点如果有左孩子，但是没有右孩子，则直接添加新节点作为右孩子，
        // 则该节点由孩子不健全变为健全，需要从队列中移除掉
        else {
            queue.peek().right = treeNode;
            queue.offer(treeNode);
            return queue.poll().getVal();
        }
    }

    public TreeNode getRoot(){
        return this.root;
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
        node3.left = node6;


        CBTInserter cbtInserter = new CBTInserter(node1);
        System.out.println(cbtInserter.insert(7));
        System.out.println(cbtInserter.insert(8));
    }
}
