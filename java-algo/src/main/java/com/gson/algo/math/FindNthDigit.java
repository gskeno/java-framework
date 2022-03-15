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
    /**
     * @return
     */
    public int findNthDigit(int n) {
        // 1位数字用尽时占用的数字字符串长度为 10
        // 2位数字用尽时占用的数字字符串长度为 10 + (10^2-10^1)*2 = 190
        // 3位数字用尽时占用的数字字符串长度为 10 + (10^2-10^1)*2 + （10^3-10^2)*3 = 190 + 2700 = 2890
        // 4位数字用尽时占用的数字字符串长度为 10 + (10^2-10^1)*2 + （10^3-10^2)*3 +（10^4-10^3)*4
        // 那么数字字符串下标为n时，当时表示的是几位数字呢？
        int needDigit = 1;
        int lengthOnNeedDigit = 10;
        int nextDigit = 2;
        int lengthOnNextDigit = 10 + (100-10)*2;
        for (; (n+1) > lengthOnNeedDigit; ) {
            int temp = lengthOnNextDigit;
            lengthOnNextDigit += ((int) Math.pow(10, nextDigit + 1) - (int) Math.pow(10, nextDigit)) * (nextDigit + 1);
            lengthOnNeedDigit = temp;
            needDigit++;
            nextDigit++;
        }
        return needDigit;
    }


    public int findNthDigit2(int n) {
        // 1位数字用尽时占用的数字字符串长度为 10
        // 2位数字用尽时占用的数字字符串长度为 10 + (10^2-10^1)*2 = 190
        // 3位数字用尽时占用的数字字符串长度为 10 + (10^2-10^1)*2 + （10^3-10^2)*3 = 190 + 2700 = 2890
        // 4位数字用尽时占用的数字字符串长度为 10 + (10^2-10^1)*2 + （10^3-10^2)*3 +（10^4-10^3)*4
        // 那么数字字符串下标为n时，当时表示的是几位数字呢？
        int needDigit = 1;
        int lengthOnNeedDigit = 10;
        int nextDigit = 2;
        int lengthOnNextDigit = 10 + (100-10)*2;
        for (; (n+1) > lengthOnNeedDigit; ) {
            int temp = lengthOnNextDigit;
            lengthOnNextDigit += ((int) Math.pow(10, nextDigit + 1) - (int) Math.pow(10, nextDigit)) * (nextDigit + 1);
            lengthOnNeedDigit = temp;
            needDigit++;
            nextDigit++;
        }

        // lengthOnNeedDigit- (int) Math.pow(10, needDigit) 表示 needDigit-1位数字所能表达的数字串的长度
        //
        int m = (n+1) - (lengthOnNeedDigit- (int) Math.pow(10, needDigit));
        int p = m/needDigit;
        int q = m%needDigit;
        char goal = String.valueOf(Math.pow(10, (needDigit-1)) + p).charAt(q);
        return Integer.valueOf(String.valueOf(goal));
    }

    public static void main(String[] args) {
        FindNthDigit findNthDigit = new FindNthDigit();
        System.out.println(findNthDigit.findNthDigit(0));
        System.out.println(findNthDigit.findNthDigit(9));
        System.out.println(findNthDigit.findNthDigit(10));
        System.out.println(findNthDigit.findNthDigit(189));
        System.out.println(findNthDigit.findNthDigit(190));
        System.out.println(findNthDigit.findNthDigit(2889));
        System.out.println(findNthDigit.findNthDigit(2890));
    }
}
