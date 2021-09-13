package com.gson.javajdk.math;

import org.junit.Test;

public class MathTest {

    /**
     *
     * 计算出的值真实世界里应该是正数，这里依然是负数
     */
    @Test
    public void testLongMinValueDivideNegative1(){
        Long a = Long.MIN_VALUE;
        System.out.println(a);
        // /-1被优化为一条 取负指令，原所有bit位取反，末尾再加1
        long l = a / -1;
        System.out.println(l);

        long b = -1L;
        long c= a/b; // 指令无法优化，这里是一条除指令，在C编译器下会溢出
        System.out.println(c);
    }

    @Test
    public void testDoubleAdd(){
        float a = 3.14f;
        System.out.println(a);
        double v1 = 1e-60;
        double v = (a + v1) - v1;
        // != 3.14
        System.out.println(v);


        System.out.println(1e3);
    }

    @Test
    public void testFloatDoubleConvert(){
        double d = 1e40;
        float f = (float)d;
        System.out.println(f);


        float c = (float)1e20;
        double d1= 1.0d;

        System.out.println( (c+ d1) -c);
    }

    @Test
    public void testIntDoubleConvert(){
        double d = 1e9;
        System.out.println(d);
        int i = 1000000000;

        // 大小相等，都等于10的9次方
        System.out.println(d == i);

        double d1 = 1e10;
        System.out.println(d1);
        // d1 >> Integer.MAX_VALUE，所以有所舍弃
        System.out.println((int) d1);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void testShortIntConvert(){
        short s = -8196;
        System.out.println(Short.toUnsignedInt(s));
        System.out.println(Integer.toHexString(Short.toUnsignedInt(s)));

        int i = (int)s;
        System.out.println(i);
        System.out.println(Integer.toHexString(i));
    }

    @Test
    public void testMultiple(){
        byte a = (byte)0b11110101; // F5H = -11
        System.out.println(a);

        byte b = (byte)0b11101110; // EEH = -18
        System.out.println(b);

        //两者相乘，会溢出
        byte c = (byte)(a*b);
        System.out.println(c);
    }
}
