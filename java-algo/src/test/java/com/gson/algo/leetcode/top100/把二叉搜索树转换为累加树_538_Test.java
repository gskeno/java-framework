package com.gson.algo.leetcode.top100;

import org.junit.Before;
import org.junit.Test;

public class 把二叉搜索树转换为累加树_538_Test {

    /**
     *      2                    5
     *    1     3   转变为     6     3
     */
    private TreeNode root1;


    private TreeNode root2;

    @Before
    public void before(){
        root1 = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root1.left = left;
        root1.right = right;


        root2 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        root2.left = node1;
        root2.right = node6;

        TreeNode node0 = new TreeNode(0);
        TreeNode node2 = new TreeNode(2);
        node1.left = node0;
        node1.right = node2;

        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        node6.left = node5;
        node6.right = node7;

        TreeNode node3 = new TreeNode(3);
        node2.right = node3;

        TreeNode node8 = new TreeNode(8);
        node7.right = node8;

    }

    @Test
    public void test1(){
        把二叉搜索树转换为累加树_538 solution = new 把二叉搜索树转换为累加树_538();
        TreeNode root = solution.convertBST(root1);
        System.out.println(root);
    }

    @Test
    public void test2(){
        把二叉搜索树转换为累加树_538 solution = new 把二叉搜索树转换为累加树_538();
        TreeNode root = solution.convertBST(root2);
        System.out.println(root);
    }
}
