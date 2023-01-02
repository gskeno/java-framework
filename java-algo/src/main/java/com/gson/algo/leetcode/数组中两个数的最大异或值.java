package com.gson.algo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class 数组中两个数的最大异或值 {

    /**
     * x = ai ^ aj则有
     * aj = x ^ ai
     *
     * 注意到
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int x = 0;
        // int的最高位是符号位0，不考虑
        // 从左到右最高位编号位30，最低位编号为0, 则总计31个位，加上符号位，总共32个位
        int HIGH_BIT = 30;
        for (int k = HIGH_BIT ; k >=0; k --){
            // 存储 nums中从最高位(含) 到第k位(含)的数值, pre^k(aj)放入到哈希表中
            Set<Integer> seen = new HashSet<>();
            for(int num : nums){
                seen.add( num >> k);
            }

            // x此时表示从最高位 到 第k+1位(含) 为止的部分
            // 我们理想地将 第k位也置为1，随后进行判断修正
            int xNext = 2 * x + 1;

            // 第k位是否可以为1
            boolean found = false;
            // 枚举i
            for(int num : nums){
                if (seen.contains(xNext ^ (num >> k))){
                    found = true;
                    break;
                }
            }
            // 确实找到了一个ai可以使 aj = x ^ ai
            if (found){
                x = xNext;
            }
            // 找不到，则第k位只能置为0，这就是修正
            else {
                x = xNext - 1;
            }
        }
        return x;
    }

    public static void main(String[] args) {

    }
}
