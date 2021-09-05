package com.gson.javajdk.lang;

import org.junit.Test;

/**
 * 数学相关测试
 */
public class MathTest {
    @Test
    public void testDivide1(){
        int a= 0x80000000;
        System.out.println(a);
        int b = a /-1;
        System.out.println(b);
    }

    @Test
    public void testDivide2(){
        int a= 0x80000000;
        int b = -1;
        int c = a/b;
        System.out.println(c);
    }

    @Test
    public void testHex1(){
        short a = (short)0xFBFB;
        System.out.println(a); // -1029

        String s = Integer.toHexString(8196);
        System.out.println(s);
        System.out.println(0xDFFC);

        System.out.println(0x8000);
    }

    @Test
    public void testShort1(){
        System.out.println(Short.toUnsignedInt((short)-32768));
        double d = 1.2345e3;
        System.out.println(Double.toHexString(d));

        System.out.println(d);
    }

    @Test
    public void testFloat(){


        float f = 1.2345e3f;
        System.out.println(Float.toHexString(f));

        int i = (int)f;
        System.out.println(i);

        // 0x1.0p-1 p后面表示指数
        System.out.println(Float.toHexString(0.5f));
        // -0x1.0p-1
        System.out.println(Float.toHexString(-0.5f));

    }
}
