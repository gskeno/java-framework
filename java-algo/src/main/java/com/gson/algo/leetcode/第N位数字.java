package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/nth-digit/
 * 给你一个整数 n ，请你在无限的整数序列[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第n 位上的数字。
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 */
public class 第N位数字 {

    /**
     * 二分法的思想，我们需要知道第N位数字归属的整数值是几位数，且是该位数下的数值范围内的哪个数
     * 1位数有 9个
     * 2位数有 90个
     * 3位数有 900个
     * x位数有 9*10^(x-1)个， 占用x * 9*10^(x-1)个位
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        // 最少1位数
        int left = 1;
        // 最多9位数
        int right = 9;
        // 最经典的二分法
        while (left < right){
            int mid = (left + right)/2;
            // mid位数的整数序列 需要的总位数
            int totalDigits = getTotalDigits(mid);
            if (totalDigits >= n){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        // n是x位数中一个数的某一位
        int x = left;
        // (x-1)位数 所占用的位数总和
        int totalDigitsOfX = getTotalDigits(x-1);
        // x位数的第idx个数
        int idx = (n - totalDigitsOfX) / x ;
        // x位数的第idx个数的第mod个数字位
        int mod = (n-totalDigitsOfX) % x;
        int num = (int)Math.pow(10, x-1) + idx;
        if (mod == 0){
            return (num - 1)%((int)Math.pow(10, x-1));
        }
        return Integer.valueOf(String.valueOf(num).substring(mod-1, mod));
    }

    /**
     * 1到length位数，总共需要的位
     * @param length   1<=length<=9，因为length=9时，返回值接近Integer.MAX_VALUE
     * @return
     */
    public int getTotalDigits(int length){
        int totalDigits = 0;
        // 1位数的每个数值占1位
        int curLength = 1;
        // 1位数共有9个
        int curCount = 9;
        while (curLength <= length){
            totalDigits += curLength * curCount;
            curLength++;
            curCount = curCount * 10;
        }
        return totalDigits;
    }


    public static void main(String[] args) {
        第N位数字 solution = new 第N位数字();
        System.out.println(solution.getTotalDigits(9));

        int ans = solution.findNthDigit(11);
        System.out.println(ans);
        ans = solution.findNthDigit(12);
        System.out.println(ans);
        ans = solution.findNthDigit(13);
        System.out.println(ans);
        ans = solution.findNthDigit(14);
        System.out.println(ans);
        ans = solution.findNthDigit(15);
        System.out.println(ans);
        ans = solution.findNthDigit(16);
        System.out.println(ans);
        ans = solution.findNthDigit(17 );
        System.out.println(ans);
    }
}
