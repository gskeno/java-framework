package com.gson.javajdk.clazzloader;

public class SubClass1 extends SuperClass1{
    static {
        System.out.println("SuperClass static init");
    }
}
