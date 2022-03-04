package com.gson.algo.dynamic.plan.knapsack;

/**
 * 分割等和子集
 * 给定一个非空的正整数数组，判断是否能将其分割成和相等的两部分
 * 如 [3,4,1] 可以分割成 [3,1]和[4]，和相等
 * [1,2,3,5]就不可以
 *
 * 属于背包问题
 */
public class DivideEquivalentSubSet {

    /**
     * 假设f(i,j)表示 能否 从前i个物品(物品编号为0,1, ..., i-1)中选择若干物品 放满容量为j的背包。
     * 如果总共有n个物品，背包的容量的为t，则f(n,t)就是问题的解
     *
     *  几个重要事实
     *  1. f(i,0) == true。任意个物品，只要都不选择，则就能装满容量为0的背包
     *  2. f(0,j) == false。从0个物品中，不论怎么挑选，都不能装满容量为j(j>0)的背包
     *  3. f(i,j) == f(i-1,j) | f(i-1, j-nums[i-1])。
     *  第i个物品不选，能否装满容量为j的背包，就依赖前(n-1)个物品了
     *  第i个物品选择，能否装满容量为j的背包，就依赖前(n-1)个物品能否达到容量j-nums[i-1]
     *  这两个选择，有一个结果为true,则f(i,j)==true。
     */
    public boolean canPartition(int[] nums){
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if (sum %2 == 1){
            return false;
        }

        return subSet(nums, sum /2);
    }

    private boolean subSet(int[] nums, int target){
        // dp存储f(i,j)的结果，由于i为nums元素的个数, j为target。
        // f(i,j)是dp二维数组的元素，要能装载到二维数组中, 所以定义数组大小时都需要+1
        Boolean[][] dp = new Boolean[nums.length + 1][ target + 1];
        return false;
    }

    public static void main(String[] args) {

    }
}
