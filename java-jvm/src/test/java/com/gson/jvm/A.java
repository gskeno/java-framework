package com.gson.jvm;

/**
 * Created by gaosong on 2017-12-31
 */
public  class A {
    public A(){
        System.out.println("this is A constructor" + Thread.currentThread().getContextClassLoader());
    }


    public  void sayHi(){
        System.out.println("this is A " + Thread.currentThread().getContextClassLoader());

    }
}
