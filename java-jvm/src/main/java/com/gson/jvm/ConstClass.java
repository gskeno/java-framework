package com.gsonkeno.jdk.training.jvm;

/**
 * Created by gaosong on 2018-01-03
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLOWORLD = "helloword";
}
