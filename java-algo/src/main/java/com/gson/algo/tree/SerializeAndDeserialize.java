package com.gson.algo.tree;

import com.gson.algo.TreeNode;

/**
 * https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=13&tqId=11214&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 反序列化二叉树
 */
public class SerializeAndDeserialize {
    /**
     * 使用前序遍历进行序列化(递归)
     * @param node
     * @return
     */
    String Serialize(TreeNode node) {
        if (node == null) {
            return "#";
        } else {
            return node.val + "," + Serialize(node.left) + "," + Serialize(node.right);
        }
    }

    /**
     * 使用index来设置树节点的val值，递归遍历左节点和右节点，如果值是#则表示是空节点，直接返回
     * @param str
     * @return
     */
    int index = -1;
    TreeNode Deserialize(String str) {
        //将序列化之后的序列用，分隔符转化为数组
        String[] s = str.split(",");
        //索引每次加一
        index++;
        int len = s.length;
        if (index > len) {
            return null;
        }
        // 空节点则直接返回null,
        if (s[index].equals("#")){
            return null;
        }
        //不是叶子节点 继续走。递归
        TreeNode  treeNode = new TreeNode(Integer.parseInt(s[index]));
        treeNode.left = Deserialize(str);
        treeNode.right = Deserialize(str);

        return treeNode;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;

        SerializeAndDeserialize serializeTree = new SerializeAndDeserialize();


        //        1
        //     2      3
        //  4       5    6
        // # #     #  # #  #
        String str = serializeTree.Serialize(treeNode1);
        System.out.println(str);
        TreeNode treeNode = serializeTree.Deserialize(str);
        System.out.println(treeNode);
    }

}
