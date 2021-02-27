package com.gson.algo.bit;

/**
 * https://blog.nowcoder.net/n/07f2bd03162d40ddaebefd666e0d71b2
 * 不用加减乘除做加法
 *
 */
public class Add {
    public int Add(int num1,int num2) {
        int result = 0;
        int carry = 0;
        do{
            result = num1 ^ num2;       //不带进位的加法
            carry = (num1 & num2) << 1; //进位
            num1 = result;
            num2 = carry;
        }while(carry != 0); // 进位不为0则继续执行加法处理进位
        return result;
    }
}
