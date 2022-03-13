package com.gson.algo.tree;

import com.gson.algo.TreeNode;
import org.junit.Assert;
import org.junit.Test;

public class SearchTreeTest {
    @Test
    public void test1(){
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode8 = new TreeNode(8);

        treeNode5.left = treeNode3;
        treeNode5.right = treeNode7;
        treeNode3.left = treeNode2;
        treeNode3.right = treeNode4;
        treeNode7.left = treeNode6;
        treeNode7.right = treeNode8;
//             5
//        3         7
//    2      4   6      8
        SearchTree searchTree = new SearchTree();
        TreeNode treeNode = searchTree.kthNode(treeNode5, 3);
        System.out.println(treeNode.val);

        treeNode = searchTree.kthNode(treeNode5, 8);
        Assert.assertNull(treeNode);
    }

    @Test
    public void test2(){
        SearchTree searchTree = new SearchTree();
        TreeNode treeNode5 = new TreeNode(5);

        TreeNode treeNode = searchTree.kthNode(treeNode5, 1);
        System.out.println(treeNode.val);
        Assert.assertEquals(treeNode.val,  5);
    }
}
