package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 * 
 * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
 *
 * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
 *
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
 *
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：boxes = "110"
 * 输出：[1,1,3]
 * 解释：每个盒子对应的最小操作数如下：
 * 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
 * 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
 * 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
 *
 */
public class 移动所有球到每个盒子所需的最小操作数 {
    public int[] minOperations(String boxes) {
        int[] ans = new int[boxes.length()];

        // 当前处理盒子左侧小球的个数
        int leftBalls = 0;
        int[] ballsAndMoveCounts = getBallsAndMoveCounts(boxes);
        // 当前处理盒子右侧小球的个数
        int rightBalls = ballsAndMoveCounts[0];
        ans[0] = ballsAndMoveCounts[1];
        for (int idx = 1; idx < boxes.length(); idx++) {
            ans[idx] = ans[idx - 1];
            if (boxes.charAt(idx - 1) == '1'){
                leftBalls++;
            }
            ans[idx] += leftBalls;
            ans[idx] -= rightBalls;

            if (boxes.charAt(idx) == '1'){
                rightBalls--;
            }
        }
        return ans;
    }
    /**
     * 返回第一个盒子，右侧的小球个数和小球全部移动到第一个盒子所需的操作次数
     * @param boxes
     * @return
     */
    private int[] getBallsAndMoveCounts(String boxes){
        int n = boxes.length();
        int balls = 0;
        int moveCounts = 0;
        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i) == '1'){
                balls++;
                moveCounts += i;
            }
        }
        return new int[]{balls, moveCounts};
    }

    public static void main(String[] args) {
        移动所有球到每个盒子所需的最小操作数 solution = new 移动所有球到每个盒子所需的最小操作数();
        int[] ans;
        ans = solution.minOperations("110");
        System.out.println(Arrays.toString(ans));
        ans = solution.minOperations("001011");
        System.out.println(Arrays.toString(ans));
    }


}
