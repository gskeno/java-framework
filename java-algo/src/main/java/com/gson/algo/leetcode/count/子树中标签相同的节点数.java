package com.gson.algo.leetcode.count;

import java.util.*;

/**
 * https://leetcode.cn/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */
public class 子树中标签相同的节点数 {
    // 父节点编号--->子节点编号 的映射
    Map<Integer, List<Integer>> parentToChildren = new HashMap<>();
    // 节点编号--->字母 的映射
    String nodeToLetter;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        parentToChildren.clear();
        nodeToLetter = labels;
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            List<Integer> children = parentToChildren.get(from);
            if (children == null){
                children = new ArrayList<>();
                parentToChildren.put(from, children);
            }
            children.add(to);
        }

        int[] ans = new int[n];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = findVal(i, nodeToLetter.charAt(i));
        }
        return ans;
    }

    /**
     * 寻找node节点的子节点下等于target的节点数量
     * @param node
     * @param target
     * @return
     */
    private int findVal(int node, char target){
        int base = nodeToLetter.charAt(node) == target ? 1 : 0;
        List<Integer> children = parentToChildren.get(node);
        if (children == null){
            return base;
        }
        int ans = 0;
        for(int child : children){
            ans += findVal(child, target);
        }
        return ans + base;
    }

    public static void main(String[] args) {
        子树中标签相同的节点数 solution = new 子树中标签相同的节点数();
        int[] ans ;
        ans = solution.countSubTrees(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd");
        System.out.println(Arrays.toString(ans));

        ans = solution.countSubTrees(4, new int[][]{{0, 1}, {1, 2}, {0, 3}}, "bbbb");
        System.out.println(Arrays.toString(ans));

        ans = solution.countSubTrees(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {0, 4}}, "aabab");
        System.out.println(Arrays.toString(ans));
    }
}
