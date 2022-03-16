package com.gson.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993
 * 把数组排成最小的数
 *
 * 输入一个非负整数数组numbers，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组[3，32，321]，则打印出这三个数字能排成的最小数字为321323。
 * 1.输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 2.拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class PrintMinNumber {
    public String printMinNumber(int [] numbers) {
        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2+o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        PrintMinNumber printMinNumber = new PrintMinNumber();
        int[] nums = {3,32,321};
        String s = printMinNumber.printMinNumber(nums);
        System.out.println(s);

    }
}
