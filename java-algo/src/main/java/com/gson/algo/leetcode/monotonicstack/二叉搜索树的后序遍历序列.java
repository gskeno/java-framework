package com.gson.algo.leetcode.monotonicstack;

/**
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class 二叉搜索树的后序遍历序列 {

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length <= 2){
            return true;
        }
        return isPostOrder(postorder, 0, postorder.length - 1);
    }

    /**
     * 数组postorder i到j区间是否满足后序遍历
     * @param postorder
     * @param i
     * @param j
     * @return
     */
    private boolean isPostOrder(int[] postorder , int i, int j){
        if ( j == i ){
            return true;
        }
        int lastNum = postorder[j];
        // i~(j-1)区间的前一部分元素都要小于 lastNum， 后一部分(可以不存在) 元素要大于 lastNum
        int pivot = -1;
        for (int k = i; k < j; k++) {
            if (postorder[k] > lastNum){
                pivot = k;
                break;
            }
        }
        if (pivot == -1){
            return isPostOrder(postorder, i, j-1);
        }
        for (int k = pivot; k < j ; k++) {
            if (postorder[k] <= lastNum){
                return false;
            }
        }

        // 当前区间i~j， 所有元素都比nums[j]大
        if (pivot == i){
            return isPostOrder(postorder, pivot, j-1);
        }

        return isPostOrder(postorder, i, pivot-1) && isPostOrder(postorder, pivot, j-1);
    }

    public static void main(String[] args) {
        二叉搜索树的后序遍历序列 solution = new 二叉搜索树的后序遍历序列();
        boolean ans;

        ans = solution.verifyPostorder(new int[]{1,2,5,10,6,9,4,3});
        System.out.println(ans);


        ans = solution.verifyPostorder(new int[]{1, 3, 2, 6, 5});
        System.out.println(ans);

        ans = solution.verifyPostorder(new int[]{1, 6, 3,2, 5});
        System.out.println(ans);
    }
}
