package com.gson.javajdk.clazzloader;

import org.junit.Test;
import sun.misc.Launcher;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.sql.*;

public class ClassLoaderTest {

    /**
     * 几个重要的源码
     * {@link Launcher#Launcher()}
     * {@link ClassLoader#parent}
     * {@link ClassLoader#loadClass(String)}
     * {@link java.sql.DriverManager#getConnection(String)}
     * {@link ClassLoader#getSystemClassLoader()}
     * @param args
     */
    public static void main(String[] args) {
        // 应用类 加载器
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println("------------");

        // 扩展类 加载器。
        // 该环境变量指定的路径下的jar包中的类被扩展类加载器所加载
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(DNSNameService.class.getClassLoader());
        System.out.println("------------");

        // 启动类加载器
        // 该环境变量指定的路径下的jar包中的类被启动类加载器所加载
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(String.class.getClassLoader());
        System.out.println("Launcher类的加载器是" + Launcher.class.getClassLoader());

        Launcher launcher = new Launcher();
        System.out.println("Launcher实例的类加载器是" + launcher.getClassLoader());

        System.out.println("ClassLoader.getSystemClassLoader " + ClassLoader.getSystemClassLoader());
    }

    /**
     * 类加载器只会加载直接定义静态字段的类，注意"直接"两字
     */
    @Test
    public void test1(){
        System.out.println(SubClass.ABC);
    }

    @Test
    public void test2(){
        // 只会输出abc
        // 常量在编译阶段会存入调用类的常量池中，也就是说Main类对SubClass1.ABC的引用
        // 已经与SuperClass1无关了，实际上已经转行为Main类对ABC字符串的引用了
        System.out.println(SubClass1.ABC);
    }

    @Test
    public void testAppClassLoader(){
        // 应用类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
    }

    @Test
    public void testClassForName(){
        try {
            // 会报错，重点在看源码
            Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection("localhost:127.0.0.1:3306/mysql");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
