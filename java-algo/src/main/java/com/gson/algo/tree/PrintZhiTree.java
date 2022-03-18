package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0
 * 按之字形顺序打印二叉树
 */
public class PrintZhiTree {
    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> Print(TreeNode root) {
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        ArrayList<Integer> curLine = new ArrayList<>();
        stack.push(root);
        while (true){
            curLine.clear();
            while (!stack.isEmpty()){
                TreeNode ele = stack.pop();
                curLine.add(ele.val);
                if (ele.left != null){
                    stack1.push(ele.left);
                }
                if (ele.right != null){
                    stack1.push(ele.right);
                }
            }
            result.add(new ArrayList<>(curLine));
            if (stack1.isEmpty()){
                break;
            }
            curLine.clear();
            while (!stack1.isEmpty()){
                TreeNode ele = stack1.pop();
                curLine.add(ele.val);
                if (ele.right != null){
                    stack.push(ele.right);
                }
                if (ele.left != null){
                    stack.push(ele.left);
                }
            }
            result.add(new ArrayList<>(curLine));
            if (stack.isEmpty()){
                break;
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left  = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        PrintZhiTree printZhiTree = new PrintZhiTree();
        ArrayList<ArrayList<Integer>> result = printZhiTree.Print(node1);
        System.out.println(result);
    }
}
