package com.gson.lang;

import java.util.Objects;

public class LongTests {

    public static void main(String[] args) {
        //连个Long对象，都为空时认为一致，都不为空且相等认为是一致的。

        Long a = null;
        Long b = null;

        System.out.println(Objects.equals(a,b));

        a = null;
        b = 1L;
        System.out.println(Objects.equals(a,b));

        a = 8739814L;
        b = 8739814L;
        System.out.println(Objects.equals(a,b));
    }
}
