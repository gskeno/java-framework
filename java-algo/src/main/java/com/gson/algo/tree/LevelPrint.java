package com.gson.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class LevelPrint {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<TreeNode> levelParent = new ArrayList<>();
        ArrayList<TreeNode> levelChild = new ArrayList<>();
        ArrayList<ArrayList<Integer>> printLevels = new ArrayList<>();
        if (pRoot == null ){
            return printLevels;
        }
        levelParent.add(pRoot);
        while (true){
            ArrayList<Integer> parentLevelVals = new ArrayList<>();
            for (int i = 0; i < levelParent.size(); i++) {
                TreeNode treeNode = levelParent.get(i);
                parentLevelVals.add(treeNode.val);

                if (treeNode.left != null){
                    levelChild.add(treeNode.left);
                }
                if (treeNode.right != null){
                    levelChild.add(treeNode.right);
                }
            }
            levelParent.clear();
            printLevels.add(parentLevelVals);
            if (levelChild.size() == 0){
                return printLevels;
            }

            ArrayList<Integer> childLevelVals = new ArrayList<>();
            for (int i = 0; i < levelChild.size(); i++) {
                TreeNode treeNode = levelChild.get(i);
                childLevelVals.add(treeNode.val);

                if (treeNode.left != null){
                    levelParent.add(treeNode.left);
                }
                if (treeNode.right != null){
                    levelParent.add(treeNode.right);
                }
            }
            levelChild.clear();
            printLevels.add(childLevelVals);
            if (levelParent.size() == 0){
                return printLevels;
            }
        }

    }
}
