package com.gson.roaring.bitmap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.roaringbitmap.RoaringBitmap;

import java.util.Arrays;


public class RoaringBitmapTest {
    private static RoaringBitmap rb1 ;
    private static RoaringBitmap rb2;

    @BeforeAll
    public static void beforeAll(){
         rb1 = RoaringBitmap.bitmapOf(1,2,10);
         rb2 = RoaringBitmap.bitmapOf(1,10,12);
    }

    /**
     * 取并集
     */
    @Test
    public void testOR(){
        rb1.or(rb2);
        System.out.println(Arrays.toString(rb1.toArray()));
    }

    /**
     * 取交集
     */
    @Test
    public void testAnd(){
        rb1.and(rb2);
        System.out.println(Arrays.toString(rb1.toArray()));
    }

    /**
     * 异或
     */
    @Test
    public void testXOR(){
        rb1.xor(rb2);
        System.out.println(Arrays.toString(rb1.toArray()));
    }

    /**
     * rb1或rb2的反集
     */
    @Test
    public void testOrNot(){
        rb1.orNot(rb2, 14);
        System.out.println(Arrays.toString(rb1.toArray()));
    }

    /**
     * rb1与上rb2的反集
     */
    @Test
    public void testAndNot(){
        rb1.andNot(rb2);
        System.out.println(Arrays.toString(rb1.toArray()));
    }

}
