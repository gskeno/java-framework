package com.gson.javajdk.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ByteTests {

    public static void main(String[] args) {
        byte[] a = null;

        System.out.println(a);
        System.out.println(Arrays.toString(a));

        a= new byte[]{0,1,0,1};

        System.out.println(Arrays.toString(a));
        System.out.println(a);
    }

    @Test
    public void testBit(){
        System.out.println(1 << 2);

        //相同位置都为1，结果才为1
        // B11110 & B10000 = B10000=16
        System.out.println(30 & 1 << 4);

        //B11110 & B01000 = B01000 = 8
        System.out.println(30 & 1<< 3);
    }

    @Test
    public void express(){
        byte a = 0b0000001000;
        Assert.assertEquals(a, 8);

        byte b = 0b100_0000;
        Assert.assertEquals(b, 64);
    }
}
