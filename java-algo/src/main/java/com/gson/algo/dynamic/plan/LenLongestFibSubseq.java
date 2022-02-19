package com.gson.algo.dynamic.plan;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长斐波那契数列
 * 如[1,2,3,4,5,6,7,8]这个原始数组A的最长斐波那契数列的长度为5，数列为1,2,3,5,8
 * 定义f(i,j)表示以A[i]为倒数第一个元素,A[j]为倒数第二个元素的斐波那契数列的长度。 i>j,
 * 且可能存在k, j>k, 满足以下等式
 * f(i,j) = f(j,k) + 1， 则A[k], A[j], A[i]就满足成为斐波那契数列的条件
 */
public class LenLongestFibSubseq {

    public int lenLongestFibSubSeq(int[] A){
        // 元素值为key， 下标为value
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }

        // dp二维数组存储f(i)(j)的值， i作为dp二维数组的行, j作为dp二维数组的列,
        // 则i范围为1到A.length-1, j范围为0到A.length-2
        int[][] dp = new int[A.length][A.length];
        int result = 2;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                // 看元素数组里是否存在k，满足 f(j,k) + 1 = f(i,j), 也即 A[k] + A[j] = A[i]
                int k = map.getOrDefault(A[i] - A[j], -1);
                // 每个dp元素进行赋值, 存在满足条件的k，则斐波那契数列长度+1； 否则长度值写2
                dp[i][j] =  k >= 0 && k < j ? dp[j][k] + 1 : 2;

                result = Math.max(result, dp[i][j]);
            }
        }
        // 最终斐波那契数列的长度应该大于2，否则，就没有满足条件的斐波那契数列，返回长度0
        return result > 2 ? result : 0;
    }

    public static void main(String[] args) {
        LenLongestFibSubseq lenLongestFibSubseq = new LenLongestFibSubseq();
        int[] A = {1,2,3,4,5,6,7,8};
        int len = lenLongestFibSubseq.lenLongestFibSubSeq(A);
        System.out.println(len);
    }
}
