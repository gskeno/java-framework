package com.gson.javajdk;

public class BigObject {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("BigObject" + this + "被回收了");
    }
}
