package com.gsonkeno.jdk.training.jvm;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by gaosong on 2017-12-29
 */
public class Test {
    public Test() {
//        System.out.println(this.getClass().getClassLoader().toString());

        try{
            System.out.println(Thread.currentThread().getContextClassLoader());
            URL url = new URL("file:D:/ftp/c/testclassloader.jar");
            URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url});
            Thread.currentThread().setContextClassLoader(myClassLoader);
            Class myClass = Thread.currentThread().getContextClassLoader().loadClass("com.gsonkeno.jdk.training.jvm.TestClassLoader");
            //Class myClass = myClassLoader.loadClass("com.gsonkeno.jdk.training.jvm.TestClassLoader");
            Object o = myClass.newInstance();

            ((TestInterface)o).disPlay();
            ((A)o).sayHi();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test();
    }
}
