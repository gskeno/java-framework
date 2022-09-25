package com.gsonkeno.jdk.training.jvm;

import org.junit.Test;

/**
 * Created by gaosong on 2017-12-29
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
    }

    @Test
    public void test() throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //该方法加载类，不会初始化代码块
        classLoader.loadClass("com.gsonkeno.jdk.training.jvm.Test2");

        //将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
        Class.forName("com.gsonkeno.jdk.training.jvm.Test2");

        //带参函数也可控制是否加载static块
        Class.forName("com.gsonkeno.jdk.training.jvm.Test2",false,classLoader);

    }
}
