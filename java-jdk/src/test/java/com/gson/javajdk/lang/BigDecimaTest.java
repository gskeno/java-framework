package com.gson.javajdk.lang;

import java.math.BigDecimal;

public class BigDecimaTest {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(9);
        BigDecimal b = a;

        b = BigDecimal.ZERO;

        System.out.println(a);

        String  s = null;
        BigDecimal c = new BigDecimal(s);
        System.out.println(c.longValue());
    }
}
