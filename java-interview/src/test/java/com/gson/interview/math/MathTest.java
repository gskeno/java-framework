package com.gson.interview.math;

import org.junit.Test;

public class MathTest {
    @Test
    public void test(){
        float a = (float)(Integer.MAX_VALUE);
        float b = (float)(Integer.MAX_VALUE - 1);
        System.out.println(a);
        System.out.println(b);

        Long d = 1L + Integer.MAX_VALUE;
        System.out.println(Math.round(d));
    }
}
