package com.gson.lang;

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
}
