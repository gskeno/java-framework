package com.gson.algo.math;

/**
 * 数字序列中某一位的数字
 * <p>
 * https://www.nowcoder.com/practice/29311ff7404d44e0b07077f4201418f5
 * <p>
 * 数字以 0123456789101112131415... 的格式作为一个字符序列，
 * 在这个序列中第 2 位（从下标 0 开始计算）是 2 ，
 * 第 9  位是 9
 * 第 10 位是 1 ，
 * 第 13 位是 1 ，以此类题，请你输出第 n 位对应的数字。
 */
public class FindNthDigit {


    public int findNthDigit2(int n) {
        if (n == 0){
            return 0;
        }
        int needDigit = 1;
        long baseSegmentLen = 0;
        while (true){
            long numsCount = needDigit == 1? 10: (int)Math.pow(10, needDigit-1) * 9 * needDigit;
            if (baseSegmentLen + numsCount >= n){
                break;
            }
            needDigit++;
            baseSegmentLen += numsCount;
        }
        System.out.println("needDigit=" + needDigit);
        // needDigit位数字所能表达的最小数字，如
        // 1位数字的最小数字是0
        // 2位数字的最小数字是10
        // 3位数字的最小数字是100
        int firstNum = needDigit == 1 ? 0 : (int)Math.pow(10, (needDigit-1));

        int m = (int)(n - baseSegmentLen);
        int p = m/needDigit;
        int q = m%needDigit;

        char goal = String.valueOf(firstNum + p).charAt(q);
        return Integer.valueOf(String.valueOf(goal));
    }

    public static void main(String[] args) {
        FindNthDigit findNthDigit = new FindNthDigit();
//        System.out.println(findNthDigit.findNthDigit(0));
//        System.out.println(findNthDigit.findNthDigit(9));
//        System.out.println(findNthDigit.findNthDigit(10));
//        System.out.println(findNthDigit.findNthDigit(189));
//        System.out.println(findNthDigit.findNthDigit(190));
//        System.out.println(findNthDigit.findNthDigit(2889));
//        System.out.println(findNthDigit.findNthDigit(2890));
//        System.out.println(findNthDigit.findNthDigit2(0));
//        System.out.println(findNthDigit.findNthDigit2(9));
//        System.out.println(findNthDigit.findNthDigit2(10));
//        System.out.println(findNthDigit.findNthDigit2(11));
//        System.out.println(findNthDigit.findNthDigit2(12));
//        System.out.println(findNthDigit.findNthDigit2(13));
//        System.out.println(findNthDigit.findNthDigit2(14));
//        System.out.println(findNthDigit.findNthDigit2(15));
//        System.out.println(findNthDigit.findNthDigit2(16));
//        System.out.println(findNthDigit.findNthDigit2(17));
//        System.out.println(findNthDigit.findNthDigit2(189));
//        System.out.println(findNthDigit.findNthDigit2(190));
//        System.out.println(findNthDigit.findNthDigit2(191));
//        System.out.println(findNthDigit.findNthDigit2(192));
//        System.out.println(findNthDigit.findNthDigit2(193));
        System.out.println(findNthDigit.findNthDigit2(1000000000));
    }
}
