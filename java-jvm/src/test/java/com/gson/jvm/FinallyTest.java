package com.gsonkeno.jdk.training.jvm;

/**
 * Created by gaosong on 2018-03-13
 */
public class FinallyTest {

    private static int a;

    //而只被final修饰的常量则既可以在声明时显式地为其赋值，也可以在类初始化时显式地为其赋值，总之，在使用前必须为其显式地赋值，系统不会为其赋予默认零值。
    private void test(){

        final int i;

        //i++;       //Variable 'i' might not have been initialized
    }
}
