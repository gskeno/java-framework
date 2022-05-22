package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class 层次遍历 {

    /**
     * 层次遍历
     */
    public List<TreeNode> traversal(TreeNode root){
        // 使用队列，先将根节点放进队列
        // 每次从队列中取出头部节点，将头部节点的左右子节点(如果存在)放入队列尾部,直到队列中无节点，则层次遍历完毕
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<TreeNode> ans = new ArrayList<>();
        TreeNode head;
        while ((head = queue.poll()) != null){
            ans.add(head);
            if (head.left != null){
                queue.add(head.left);
            }
            if (head.right != null){
                queue.add(head.right);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;

        node3.left = node4;
        node3.right = node5;
        层次遍历 solution = new 层次遍历();
        List<TreeNode> traversal = solution.traversal(root);
        List<Integer> collect = traversal.stream().map(TreeNode::getVal).collect(Collectors.toList());
        System.out.println(collect);
    }
}
