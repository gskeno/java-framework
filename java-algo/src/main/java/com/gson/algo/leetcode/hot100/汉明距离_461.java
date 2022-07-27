package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/hamming-distance/
 *
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 */
public class 汉明距离_461 {


    public int hammingDistance(int x, int y) {
        // 异或，位值不同，则结果位1，否则为0
        int m = x ^ y;
        int ans = 0;
        while (m != 0 ){
            if (m % 2 == 1){
                ans++;
            }
            m = m /2;
        }
        return ans;
    }

    public static void main(String[] args) {
        汉明距离_461 solution = new 汉明距离_461();
        System.out.println(solution.hammingDistance(1, 4));
    }
}
