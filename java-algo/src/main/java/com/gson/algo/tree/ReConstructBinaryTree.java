package com.gson.algo.tree;

import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 重建二叉树
 * <p>
 * 根据中序遍历和前序遍历可以确定二叉树，具体过程为：
 * <p>
 * 根据前序序列第一个结点确定根结点
 * 根据根结点在中序序列中的位置分割出左右两个子序列
 * 对左子树和右子树分别递归使用同样的方法继续分解
 * 例如：
 * 前序序列{1,2,4,7,3,5,6,8} = pre
 * 中序序列{4,7,2,1,5,3,8,6} = in
 * <p>
 * 根据当前前序序列的第一个结点确定根结点，为 1
 * 找到 1 在中序遍历序列中的位置，为 in[3]
 * 切割左右子树，则 in[3] 前面的为左子树， in[3] 后面的为右子树
 * 则切割后的左子树前序序列为：{2,4,7}，切割后的左子树中序序列为：{4,7,2}；切割后的右子树前序序列为：{3,5,6,8}，切割后的右子树中序序列为：{5,3,8,6}
 * 对子树分别使用同样的方法分解
 */
public class ReConstructBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 输入为节点x的前序遍历 和中序遍历
     * 输出为节点x
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0){
            return null;
        }
        //前序遍历的首元素就是根节点
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            //中序遍历的第i(从0算起)个节点 == 前序遍历的根节点
            if (in[i]== root.val){
                //那么当前节点的左节点x有什么特点呢？
                //x的前序遍历为 pre[1],...,pre[i],共有i个元素
                //x的中序遍历为 in[0],...,in[i-1],共有i个元素
                root.left =reConstructBinaryTree(
                        Arrays.copyOfRange(pre, 1, i+1),
                        Arrays.copyOfRange(in, 0, i));

                root.right = reConstructBinaryTree(
                        Arrays.copyOfRange(pre, i+1, pre.length ),
                        Arrays.copyOfRange(in, i+1, in.length));
                return root;
            }
        }
        return root;
    }

}
