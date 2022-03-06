package com.gson.algo.dynamic.plan.knapsack;

import java.util.Arrays;

/**
 * 最少的硬币数目
 * 给定正整数数组coins 表示硬币的面额和一个目标总值t,
 * 请计算出凑出总额t至少需要的硬币数目。
 * 每种硬币可以使用任意次。
 * 如果没有满足条件的硬币选择方式，则返回-1。
 *
 * 如硬币的面额为[1,3,9,10]，总额为15，则至少需要3枚硬币，即
 * 2枚面额为3的硬币和一枚面额为9的硬币。
 */
public class CoinChange {

    /**
     * 定义f(i)表示凑出总额为i的硬币需要的最少数目
     *
     * 则 f(i) = min( f(i-coins[j]) + 1 ) 当   (coins[j] <=i)
     * @param coins
     * @param target
     * @return
     */
    public int coinChange(int[] coins, int target){
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target ; i++) {
            // dp[i]表示的就是凑出总额为i的硬币所需要的最小数目
            dp[i] = target + 1;

            // 总额为i-coin所需要的硬币数目 + 硬币价值为coin的一枚硬币
            for(int coin : coins){
                if (i >= coin){
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        return dp[target] > target ? -1 : dp[target];
    }

    /**
     * 函数f(i,j)表示用前i种硬币(coins[0],coins[1], ..., coins[i-1])凑出总额为j需要的最小硬币数目
     * 则存在
     *
     * f(i,j) = min ( f(i-1, j-k*coins[i-1]) + k )    其中 k*coins[i-1] <=j
     * f(i,0) = 0;
     * f(0,j) = NAN, 其中 j>0, NAN表示没有满足条件的硬币选择方式，如果非要用数字来表示的话，可以认为f(0,j)=j+1，
     * 这是明显成立不了的
     */
    public int coinChange2(int[] coins, int target){
        int[][] dp = new int[coins.length + 1][target+1];
        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < target+1; j++) {
                dp[i][j] = target + 1;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        // dp[i][j]就等于f(i,j)
        // 从左到右处理，从上到下处理
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= target ; j++) {
                for (int k = 0; k * coins[i-1] <= j ; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-k * coins[i-1]] + k);
                }
            }
        }
        return dp[coins.length][target] > target ? -1 : dp[coins.length][target];
    }

    /**
     * 同coinChange2,但是存储只用到一维数组，每行从右往左处理，行记录之间是从上往下处理
     * @param coins
     * @param target
     * @return
     */
    public int coinChange3(int[] coins, int target){
        // 选择硬币后的目标值为target

        int[] dp = new int[target + 1];
        // 填充值为target + 1, 是为了最后最判断，如果dp[target] > target，则没有方案可以从硬币里选择组成最后最后的目标值target
        Arrays.fill(dp, target + 1);
        // 因为 f(i,0)永远为0
        dp[0] = 0;

        for(int coin : coins){
            for (int j = target; j >=1 ; j--) {
                for (int k = 1; k * coin <= j ; k++) {
                    dp[j] = Math.min(dp[j], dp[j-k*coin] + k);
                }
            }
        }

        return dp[target] > target ? -1 : dp[target];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1,3,9,10};
        int target = 15;
        int answerCounts = coinChange.coinChange(coins, target);
        System.out.println(answerCounts);

        answerCounts = coinChange.coinChange2(coins, target);
        System.out.println(answerCounts);

        answerCounts = coinChange.coinChange3(coins, target);
        System.out.println(answerCounts);
    }
}
