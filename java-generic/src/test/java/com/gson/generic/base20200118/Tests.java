package com.gson.generic.base20200118;

import java.io.Serializable;
import java.util.ArrayList;

public class Tests {
    static <T> T pick(T a, T b){
        return b;
    }

    public static void main(String[] args) {
        //推断出符合方法调用的类型参数是Serializable
        Serializable d = pick("d", new ArrayList<String>());
        System.out.println(d);

        //推断出符合方法调用的类型参数是Number
        Number pick = pick(3, 4L);

        //推断出符合方法调用的类型参数是Integer
        Integer pick1 = pick(3, 4);
    }

}
