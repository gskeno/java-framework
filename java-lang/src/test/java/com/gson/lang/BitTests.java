package com.gson.lang;

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
}
