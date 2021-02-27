package com.gson.algo.tree;

import org.junit.Assert;
import org.junit.Test;

public class SearchTreeTest {
    @Test
    public void test1(){
        SearchTree searchTree = new SearchTree();
        SearchTree.TreeNode treeNode5 = new SearchTree.TreeNode(5);
        SearchTree.TreeNode treeNode3 = new SearchTree.TreeNode(3);
        SearchTree.TreeNode treeNode7 = new SearchTree.TreeNode(7);
        SearchTree.TreeNode treeNode2 = new SearchTree.TreeNode(2);
        SearchTree.TreeNode treeNode4 = new SearchTree.TreeNode(4);
        SearchTree.TreeNode treeNode6 = new SearchTree.TreeNode(6);
        SearchTree.TreeNode treeNode8 = new SearchTree.TreeNode(8);

        treeNode5.left = treeNode3;
        treeNode5.right = treeNode7;
        treeNode3.left = treeNode2;
        treeNode3.right = treeNode4;
        treeNode7.left = treeNode6;
        treeNode7.right = treeNode8;
//             5
//        3         7
//    2      4   6      8

        SearchTree.TreeNode treeNode = searchTree.kthNode(treeNode5, 3);
        System.out.println(treeNode.val);

        treeNode = searchTree.kthNode(treeNode5, 8);
        Assert.assertNull(treeNode);
    }

    @Test
    public void test2(){
        SearchTree searchTree = new SearchTree();
        SearchTree.TreeNode treeNode5 = new SearchTree.TreeNode(5);

        SearchTree.TreeNode treeNode = searchTree.kthNode(treeNode5, 1);
        System.out.println(treeNode.val);
        Assert.assertEquals(treeNode.val,  5);
    }
}
