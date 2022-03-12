package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先遍历
 *         1
 *      2     3
 *   4    5 6    7
 *  8
 */
public class DFSSearchTree {

    /**
     * 前序遍历递归
     * @param node
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode node){
        List<Integer> result = new LinkedList<>();
        // 每个递归内容的执行动作 : 总是先遍历左子树，再遍历当前节点，再遍历右子树
        // 结束条件: 当前节点为空
        dfsPreorder(result, node);
        return result;
    }

    private void dfsPreorder(List<Integer> result, TreeNode node){
        if (node != null){
            result.add(node.val);
            dfsPreorder(result, node.left);
            dfsPreorder(result, node.right);
        }
    }

    /**
     * 前序遍历使用栈
     * @param node
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode node){
        TreeNode curNode = node;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();
        // 先按照左子节点指针找到最左子节点
        // 处理完该节点之后，按照中序遍历的原则，就要找到其右孩子，
        // 对右孩子进行上述同样的处理
        while (curNode != null || !stack.isEmpty()){
            while (curNode != null){
                // 与中序遍历类似，区别在于队列增加元素的时机不同
                result.add(curNode.val);
                stack.push(curNode);
                curNode = curNode.left;
            }
            // ensure 到这里,curNode肯定为null
            // stack栈顶元素是最下层左子节点（当是完全二叉树时是这种情况，当不是完全二叉树时，可能不是如此)
            // 比如        1
            //      NIL        3
            // 此时stack的栈顶是1
            TreeNode cur = stack.pop();

            curNode = cur.getRight();
        }
        return result;
    }

    /**
     * 中序遍历(递归)
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode node){
        List<Integer> result = new ArrayList<>();
        // 每个递归内容的执行动作 : 总是先遍历左子树，再遍历当前节点，再遍历右子树
        // 结束条件: 当前节点为空
        dfsInorder(result, node);
        return result;
    }

    private void dfsInorder(List<Integer> result, TreeNode node){
        if (node != null){
            dfsInorder(result, node.left);
            result.add(node.val);
            dfsInorder(result, node.right);
        }
    }

    /**
     * 中序遍历使用栈
     * @param node
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode node){
        TreeNode curNode = node;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();
        // 先按照左子节点指针找到最左子节点
        // 处理完该节点之后，按照中序遍历的原则，就要找到其右孩子，
        // 对右孩子进行上述同样的处理
        while (curNode != null || !stack.isEmpty()){
            while (curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }
            // ensure 到这里,curNode肯定为null
            // stack栈顶元素是最下层左子节点（当是完全二叉树时是这种情况，当不是完全二叉树时，可能不是如此)
            // 比如        1
            //      NIL        3
            // 此时stack的栈顶是1
            TreeNode cur = stack.pop();
            result.add(cur.val);
            curNode = cur.getRight();
        }
        return result;
    }

    /**
     * 后序遍历 递归
     * @param node
     * @return
     */
    public List<Integer> postTraversal1(TreeNode node){
        List<Integer> result = new LinkedList<>();
        dfsPostTraversal(result, node);
        return result;
    }

    private void dfsPostTraversal(List<Integer> result, TreeNode node){
        if (node != null){
            dfsPostTraversal(result, node.left);
            dfsPostTraversal(result, node.right);
            result.add(node.val);
        }
    }

    /**
     * 后序遍历 栈
     * 特点：当遍历达到某个节点A时，如果该节点的右子树之前还没遍历过，就得先去遍历该右子树。
     *
     * 如何知道节点A的右子树还没遍历过呢？
     *
     * 答案就是: A 存在右子树且 遍历达到的上一个节点B不是A的右孩子； 这是因为如果
     * A 存在右子树且 遍历达到的上一个节点B是A的右孩子，说明A右子树已经遍历过，现在可以
     * 直接遍历A。当然如果A没有右孩子，也是可以直接遍历A的
     * @param node
     * @return
     */
    public List<Integer> postTraversal2(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();
        TreeNode cur = node;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            // 此时, cur为空, stack里栈顶元素是相对很靠下的节点了，且栈顶元素没有左孩子
            TreeNode peek = stack.peek();
            // peek的右子树还没处理，要干它
            if (peek.right != null && peek.right != pre){
                cur = peek.right;
            }
            // peek没有右子树，或者peek的右子树已经处理了
            // 即peek.right == null || peek.right == pre
            else {
               // 记住这里要弹出栈
               stack.pop();
               result.add(peek.val);
               pre = peek;
               cur = null;
            }
        }

        return result;
    }

    /**
     * 后序遍历
     * 使用双栈
     *
     * 特点: 处理节点时A，将A入栈；如果A存在右子树，则再处理A的右子树，即A的右子节点；
     * 如果A存在左子树，再处理A的左子树，即A的左子节点。 处理子树时，处理办法同上。
     *
     * 可以知道，父节点先入栈，右子节点再入栈，左子节点再入栈，出栈时自然就是后序遍历的结果
     * 使用两个栈，
     * 一个栈会右入栈出栈的行为
     * 另一个栈只右入栈行为，出栈时结果就是后序遍历结果
     * @return
     */
    public List<Integer> postTraversal3(TreeNode root){
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur!=null){
                // 每遍历到的节点A都先入栈
                stack.push(cur);
                temp.push(cur);
                // 下一次A的右孩子B如果存在，则会接着入栈
                // 如果A的右孩子不存在，看下方的else分支,处理A的左孩子C
                cur = cur.right;
            }else {
                TreeNode pop = stack.pop();
                cur = pop.left;
            }
        }
        while (!temp.isEmpty()){
            result.add(temp.pop().val);
        }
        return result;
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
        TreeNode node7 = new TreeNode(7);
        node3.left = node6;
        node3.right = node7;

        TreeNode node8 = new TreeNode(8);
        node4.left = node8;

        DFSSearchTree dfsSearchTree = new DFSSearchTree();
        List<Integer> list = dfsSearchTree.inorderTraversal1(node1);
        System.out.println(list);

        List<Integer> list1 = dfsSearchTree.inorderTraversal2(node1);
        System.out.println(list1);

        List<Integer> list2 = dfsSearchTree.preorderTraversal1(node1);
        System.out.println(list2);
        List<Integer> list3 = dfsSearchTree.preorderTraversal2(node1);
        System.out.println(list3);

        List<Integer> list4 = dfsSearchTree.postTraversal1(node1);
        System.out.println(list4);
        List<Integer> list5 = dfsSearchTree.postTraversal2(node1);
        System.out.println(list5);
        List<Integer> list6 = dfsSearchTree.postTraversal3(node1);
        System.out.println(list6);
    }
}
