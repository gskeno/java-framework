package com.gson.util;

import org.junit.Test;

import java.util.Objects;

public class ObjectUtilTest {

    @Test
    public void testEqual(){
        int a = 2;
        Integer b = null;
        boolean equals = Objects.equals(a, b);
        System.out.println(equals);

        String c = "2";
        int d= 2;
        System.out.println(Objects.equals(c,d));


    }

}
