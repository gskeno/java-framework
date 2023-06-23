package com.gson.algo.leetcode.array;

/**
 * https://leetcode.cn/problems/count-primes/
 * 
 * 给定整数 n ，返回 所有小于非负整数n的质数的数量 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 */
public class 计数质数 {
    /**
     * 提示1: 对于任意一个数字x, 找出小于x的质数，不必遍历2到x-1，遍历1到 x^0.5即可。举例,
     *       9不是质数,因为2到3之间的3能整除9；
     *       11是质数，因为2到3.x之间的数都不能整除11。如果3之后的某个数m能整除11，则因子n=11/m肯定比11^0.5小。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime(i) ? 1: 0;
        }
        return ans;
    }

    public boolean isPrime3(int x){
        for (int i = 2; i < x; i++) {
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }
    /**
     * x是否是质数
     * @param x
     * @return
     */
    public boolean isPrime(int x){
        for (int i = 2; i * i <= x ; i++) {
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }

    public int countPrimes2(int n) {
        int ans = 0;
        // 初始时， 认为都是质数，遍历过程中将N * i修改为合数
        int[] nums = new int[n];
        for (int i = 2; i < n; i++) {
            if (nums[i] == 0){
                ans++;
                for (int k = 2; k * i < n; k++) {
                    nums[k * i] = 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        计数质数 solution = new 计数质数();
        System.out.println(solution.countPrimes2(10));
        System.out.println(solution.countPrimes2(0));
        System.out.println(solution.countPrimes2(1));
        System.out.println(solution.countPrimes2(12));
        System.out.println(solution.countPrimes2(14));
        System.out.println(solution.countPrimes2(18));
        System.out.println(solution.countPrimes2(20));
        System.out.println(solution.countPrimes2(24));
        System.out.println(solution.countPrimes2(30));
        System.out.println(solution.countPrimes2(32));
        System.out.println(solution.countPrimes2(38));
        System.out.println(solution.countPrimes2(42));
    }

}
