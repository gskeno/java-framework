package com.gson.javajdk.clazzloader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTest {
    public static void main(String[] args) {
        try {
            ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println("current Thread classLoader is " + oldClassLoader);
            URL url = new URL("file:/Users/ruchen/IdeaProjects/java-framework/java-jdk/target/java-jdk-1.0-SNAPSHOT.jar");

            System.out.println("java.class.path" + System.getProperty("java.class.path"));
            URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url});
            // Thread.currentThread().setContextClassLoader(myClassLoader);
//            Class myClass = myClassLoader.loadClass("TestMyCl");
//            myClass.newInstance();
            // myclassLoader的父类加载器是AppClassLoader
            System.out.println(myClassLoader);
            Thread.currentThread().setContextClassLoader(myClassLoader);
            Class myClass1 = myClassLoader.loadClass("com.gson.javajdk.clazzloader.TestMyCl");
            myClass1.newInstance();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
