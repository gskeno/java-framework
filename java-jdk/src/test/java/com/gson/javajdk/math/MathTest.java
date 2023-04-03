package com.gson.javajdk.math;

import org.junit.Assert;
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

    @Test
    public void testSub(){
        //  1111 1111 1110 1000
        // -0000 0000 0001 0111
        // -0000 0000 0001 1000 = -(16+8) = 24;
        short a = (short) 0xFFE8;
        System.out.println(a);

        short b = (short)0x7FE6;
        System.out.println(b);

        System.out.println(a-b);
        System.out.println(Short.MIN_VALUE);
    }

    @Test
    public void testDouble(){
        Double d = 1.0d;
        System.out.println(Double.toHexString(d));

        d = 2.0d;
        System.out.println(Double.toHexString(d));

        d = 4.0d;
        System.out.println(Double.toHexString(d));

        d = 8.0d;
        System.out.println(Double.toHexString(d));

        Float f = 1.0f;
        System.out.println(Float.toHexString(f));
    }

    @Test
    public void testPriority(){
        /**
         * 乘法优先级高于取模优先级
         */
        int m = 3*7%4;
        System.out.println(m);
    }

    @Test
    public void testLongMultiply(){
        long a = Long.MAX_VALUE/2;
        long b = Long.MAX_VALUE/2;

        long c = a * b/10000001L;
        System.out.println(c);
    }

    @Test
    public void testShortMultiply(){
        int a = Integer.MAX_VALUE-1;
        int b = Integer.MAX_VALUE-4;

        int c = a * b;
        System.out.println(c);
    }

    @Test
    public void testInt(){
        int minValue = Integer.MIN_VALUE;
        System.out.println(minValue);

        int n = (Integer.MIN_VALUE - (0-8))/10;
        System.out.println(n);
        n = (Integer.MIN_VALUE - (0-9))/10;
        System.out.println(n);
    }

    @Test
    public void testDoubleString(){
        double d = 1.541;
        System.out.println(String.format("%.1f", d));

        d = 1.5499;
        System.out.println(String.format("%.1f", d));

        //四舍五入
        d = 1.55;
        System.out.println(String.format("%.1f", d));

        //四舍五入
        d = -1.55;
        System.out.println(String.format("%.1f", d));

        //四舍五入
        d = -1.54;
        System.out.println(String.format("%.1f", d));

        //四舍五入
        d = -1.5499;
        System.out.println(String.format("%.1f", d));
    }

    @Test
    public void testMathRound(){
        System.out.println(Math.round(1.55d));
        System.out.println(Math.round(-1.5d));
        System.out.println(Math.round(-1.50001d));

        System.out.println((double) Math.round(1.74444 * 10 )/10);
    }

    @Test
    public void test异或(){
        // [A,Z] == [65, 90]
        // [a,z] == [97,122]
        // 65 = 0100 0001
        // 97 = 0110 0001
        char c = 'a';
        System.out.println((int)c);
    }

    @Test
    public void test8(){
        double a = 3.14158274686846868136864d;
        double b = 3.14158274686846868136865d;
        Assert.assertTrue(a == b);
    }


}
