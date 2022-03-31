package com.gson.javajdk.clazzloader;

public class SubClass extends SuperClass{
    static {
        System.out.println("SuperClass static init");
    }
}
