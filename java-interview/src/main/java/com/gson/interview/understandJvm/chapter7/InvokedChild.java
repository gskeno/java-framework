package com.gson.interview.understandJvm.chapter7;

public class InvokedChild extends Invoked {
    public static String a;

    static {
        System.out.println("初始化InvokedChild类");
    }
}
