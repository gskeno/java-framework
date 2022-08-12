package com.gson.algo.leetcode;

public class 整数替换_397 {

    public int integerReplacement1(int n) {
        int times = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n = n >> 1;
            } else {
                n--;
            }
            times++;
        }
        return times;
    }

    public int integerReplacement2(int n) {
        String s = Integer.toBinaryString(n);
        int times = 0;
        for (int i = 1; i < s.length(); ) {
            int count = 0;
            while (i < s.length() && s.charAt(i) == '1' ) {
                count++;
                i++;
            }
            if (count == 0) {
                times++;
                i++;
            } else {
                times+= getTransferTimes(count);
            }
        }
        return times;
    }

    private int getTransferTimes(int countOfOne) {
        if (countOfOne == 1) {
            return 2;
        } else {
            return countOfOne + 2;
        }
    }

    public int integerReplacement(int n){
        int times = 0;
        while (n != 1){
            // 偶数
            if ( (n & 1) == 0){
                n = n >>1;
                times++;
            }
            // 奇数
            else {
                // 该+1还是-1呢?
                // 末尾连续2个1
                if (n == 3){
                    n--;
                    times++;
                }else if(n == Integer.MAX_VALUE){
                    n = (n-1)/2;
                    times+=2;
                }
                // 末尾连续2个1
                else if ( (n&1) == 1 && (n&2) == 2){
                    n++;
                    times++;
                }else {
                    n--;
                    times++;
                }
            }
        }
        return times;
    }

    public static void main(String[] args) {
        整数替换_397 solution = new 整数替换_397();
//        System.out.println(solution.integerReplacement(8));
//        System.out.println(solution.integerReplacement(7));
//        System.out.println(solution.integerReplacement(4));
//        System.out.println(solution.integerReplacement(65535));
//        System.out.println(solution.integerReplacement(7));
//        System.out.println(solution.integerReplacement(4));
//        System.out.println(solution.integerReplacement(8));
//        System.out.println(solution.integerReplacement(15));
//        System.out.println(solution.integerReplacement(100000000));
        System.out.println(solution.integerReplacement(2147483647));
    }
}
