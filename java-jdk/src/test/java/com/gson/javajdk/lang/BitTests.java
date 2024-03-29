package com.gson.javajdk.lang;

import org.junit.Assert;
import org.junit.Test;

public class BitTests {

    @Test
    public void testConvertDecimal2Binary(){
        int minIntDecimalFormat = Integer.MIN_VALUE;
        //人为规定1000000000000000这个补码编码为-32768
        int minIntBinaryFormat = 0b1000_0000_0000_0000_0000_0000_0000_0000;
        Assert.assertEquals(minIntBinaryFormat, minIntDecimalFormat);

        //最小int 异或 最大int
        //补码 1000_0000_0000_0000  最小值
        //    0111_1111_1111_1111  最大值
        //    0000_0000_0000_0000  &操作结果
        int result = Integer.MIN_VALUE & Integer.MAX_VALUE;
        Assert.assertEquals(result, 0);

        int m = -9;
        int n = 2;
        Assert.assertEquals(-1, m%n);
    }

    @Test
    public void test(){
        int a = (1 << 16) -1;
        System.out.println(Long.toBinaryString(a));
        int b = (0 >>>16);
        System.out.println(Long.toBinaryString(b));

        int c = 327680 >>> 16;
        System.out.println(Long.toBinaryString(c));

        System.out.println(0 >>> 16);

        System.out.println(Long.toBinaryString(-20));
    }

    /**
     * 前导0的个数
     */
    @Test
    public void testNumberOfLeadingZeros(){
        int a = 0b11;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));

        a <<= 16;
        a <<= 8;
        a <<= 4;
        a <<= 2;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a);

        System.out.println(Integer.toBinaryString(a >>>31));

        int numberOfLeadingZeros = Integer.numberOfLeadingZeros(0b11111111_11111111);
        System.out.println(numberOfLeadingZeros);
    }

    @Test
    public void testLeftShift(){
        int a = 0b10000000_00000000_00000000_00001111;
        // 负数
        Assert.assertTrue(a < 0);
        // 正数
        Assert.assertTrue(a << 1 > 0);
    }

    @Test
    public void testLeftShift1(){
        int a = 0x80FFFFFF;
        System.out.println(a);
        System.out.println(a << 1);
        System.out.println(-8 >> 1);
        System.out.println(-8 >>> 1);
    }
}
