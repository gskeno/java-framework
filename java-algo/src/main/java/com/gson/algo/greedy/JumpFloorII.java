package com.gson.algo.greedy;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 概念定义法
 */
public class JumpFloorII {

    /**
     * 定义f[i]为跳到n级台阶的方法数
     * 假设 f[10]为跳到10级台阶的方法数
     * 那么如果上一次 跳1步到达10级台阶，那么有f[9]种方案
     *    如果上一次 跳2步到达10级台阶，那么有f[8]种方案
     *    ....
     *    如果上一次 跳9步到达10级台阶，那么有f[1]种方案
     *    如果上一次 跳10步到达10级台阶，那么有f[0]种方案
     *
     *    很明显f(0)=f(1)=1,且有公式f[i] = f[i-1] + f[i-2] + ... + f[1] + f[0]
     *
     *    时间复杂度O(n的平方)
     * @param target
     * @return
     */
    public int jumpFloorII(int target) {
        if (target ==0 || target == 1){
            return 1;
        }
        int[] cases = new int[target + 1];
        cases[0] = cases[1] = 1;
        for (int i = 2; i <= target ; i++) {
            for (int j = 0; j < i; j++) {
               cases[i] += cases[j];
            }
        }
        return cases[target];
    }

    /**
     * f[i] = f[i-1] + .... + f[1] + f[0]
     * f[i-1] = f[i-2] + ... +f[1] + f[0]
     * 得到 f[i] = 2f[i-1], 且f[1]=f[0]=1
     * @param target
     * @return
     */
    public int jumpFloorII2(int target) {
        if (target ==0 || target == 1){
            return 1;
        }
        int ret = 1;
        for (int i = 2; i <= target ; i++) {
            ret = ret *2;
        }
        return ret;
    }

}
