package com.gson.algo.tree;

import com.gson.algo.TreeNode;
import org.junit.Before;

public class TreeBasicTest {

    protected TreeNode root1;

    protected TreeNode root2;

    protected TreeNode root3;

    @Before
    public void initRoot1(){
        root1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root1.left = node2;
        root1.right = node3;

        node3.left = node4;
        node3.right = node5;
    }
}
