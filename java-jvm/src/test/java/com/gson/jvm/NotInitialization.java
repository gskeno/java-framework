package com.gsonkeno.jdk.training.jvm;

import org.junit.Test;

/**
 * 被动引用
 * Created by gaosong on 2018-01-03
 */
public class NotInitialization {

    //通过子类引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的
    //初始化
    //设置 -XX:TraceClassLoading参数可以观察到此操作会导致子类的加载
    //[Loaded com.gsonkeno.jdk.training.jvm.SuperClass from file:/E:/ideaProjects2017/java-training/jdk-training/target/classes/]
    //[Loaded com.gsonkeno.jdk.training.jvm.SubClass from file:/E:/ideaProjects2017/java-training/jdk-training/target/classes/]
    public static void main(String[] args) {
        //System.out.println(SubClass.value);
        //testArray();
        testConst();
    }

    //通过数组定义来引用类，不会触发类的初始化
    //但是会触发类的加载
    public static  void testArray(){
        SuperClass[] sca = new SuperClass[10];
    }

    //常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义
    //常量的类,所以不会加载ConstClass类
    public static void testConst(){
        System.out.println(ConstClass.HELLOWORLD);
    }
}
