package com.gson.algo.dynamic.knapsack;

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
    public boolean canPartition(int[] nums, int way){
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if (sum %2 == 1){
            return false;
        }

        if (way == 1){
            return subSet(nums, sum /2);
        }

        if (way == 2){
            return subSet2(nums, sum/2);
        }

        return subSet3(nums, sum/2);
    }

    private boolean subSet(int[] nums, int target){
        // dp存储f(i,j)的结果，由于i为nums元素的个数, j为target。
        // f(i,j)是dp二维数组的元素，要能装载到二维数组中, 所以定义数组大小时都需要+1
        Boolean[][] dp = new Boolean[nums.length + 1][ target + 1];
        return helper(nums, dp, nums.length, target);
    }

    private boolean helper(int[] nums, Boolean[][] dp, int i, int j){
        if (dp[i][j] == null){
          if (j == 0){
              dp[i][j] = true;
          }else if ( i == 0){
              dp[i][j] = false;
          }else {
              // 这里是自上而下的处理方式，层层迭进，取到最深处结果后，再徐徐返回
              // 第i个物品没有选择
              dp[i][j] = helper(nums, dp, i-1, j);

              // 第i个物品不选择的话，无法填满容量为j的背包
              // 则尝试选择第i个物品，前i-1个物品则需要能达到容量 j - nums[i-1], 这个容量值还要大于等于0
              if (!dp[i][j] && (j - nums[i-1]) >= 0){
                  dp[i][j] = helper(nums, dp, i-1, j - nums[i-1]);
              }
          }
        }

        return dp[i][j];
    }

    /**
     * 自底而上处理
     * @param nums
     * @param target
     * @return
     */
    private boolean subSet2(int[] nums, int target){
        // 默认值false
        boolean[][] dp = new boolean[nums.length + 1] [target +1];
        // 初始化f(i,0) = true
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= target ; j++) {
                // 从左向右处理，从上往下处理
                // 第i个元素不被选择(i从1开始算起)
                dp[i][j] = dp[i-1][j];
                // 第i个元素被选择
                if (!dp[i][j] && (j - nums[i-1]) >= 0){
                    dp[i][j] = dp[i-1][j- nums[i-1]];
                }
            }
        }

        return dp[nums.length][target];

    }

    private boolean subSet3(int[] nums, int target){
        Boolean dp[] = new Boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length ; i++) {
            // 在优化空间效率后，代码中f(i,j)和f(i-1,j) 都保存在dp[j]中。
            // 下方代码看起来只考虑了当选择下标为i-1时的数字时f(i,j)等于f(i-1, j-nums[i-1])时的场景。
            // 这是因为当不选择下标为i-1的数字时，f(i,j)等于f(i-1,j)。而f(i,j)和f(i-1,j)都保存在
            // dp[j]中，写成代码就是dp[j] = dp[j], 这一行代码被省略了
            for (int j = target; j >=1; j--) {
               if (( dp[j] == null || !dp[j]) && (j - nums[i-1]) >= 0){
                   dp[j] = dp[j - nums[i-1]];
                   System.out.println("dp[" + j + "] used dp[" + (j - nums[i-1] ) + "] when i=" + i );
               }
            }

            for (int j = 0; j < dp.length; j++) {
                System.out.println("d[" + i + "," + j + "]=" + dp[j]);
            }
            System.out.println("-------");
        }
        return dp[target] != null && dp[target];
    }

    public static void main(String[] args) {
        DivideEquivalentSubSet divideEquivalentSubSet = new DivideEquivalentSubSet();
        int[] nums = new int[]{3, 4, 1};
        boolean b = divideEquivalentSubSet.canPartition(nums, 1);
        System.out.println(b);

        boolean b1 = divideEquivalentSubSet.canPartition(nums, 2);
        System.out.println(b1);

        boolean b2 = divideEquivalentSubSet.canPartition(nums, 3);
        System.out.println(b2);
    }
}
