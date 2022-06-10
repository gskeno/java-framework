package com.gson.javajdk.clazz;

public class ForNameObject {
    private static int name;

    static {
        System.out.println("静态变量name在静态代码块中赋值前是" + name);
        System.out.println("执行静态代码块中");
        name = 1;
        System.out.println("静态变量name在静态代码块中赋值后是" + name);
    }

    ForNameObject(){
        System.out.println("构造函数中name=" + name);
    }

    public static int getName(){
        return name;
    }

    public int getName0(){
        return name;
    }
}
