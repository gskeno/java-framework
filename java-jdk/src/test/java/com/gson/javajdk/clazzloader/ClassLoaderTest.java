package com.gson.javajdk.clazzloader;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Launcher;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;

public class ClassLoaderTest {

    /**
     * 几个重要的源码
     * {@link Launcher#Launcher()}
     * {@link ClassLoader#parent}
     * {@link ClassLoader#loadClass(String)}
     * {@link java.sql.DriverManager#getConnection(String)}
     * {@link ClassLoader#getSystemClassLoader()}
     *
     * @param args
     */
    public static void main(String[] args) {
        // 应用类 加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        // 规范名
        String name = classLoader.getClass().getCanonicalName();
        Assert.assertEquals(name, "sun.misc.Launcher.AppClassLoader");

        // 扩展类 加载器。
        // 该环境变量指定的路径下的jar包中的类被扩展类加载器所加载
        String extDirs = System.getProperty("java.ext.dirs");
        System.out.println(extDirs);
        String extClassLoaderClassName = DNSNameService.class.getClassLoader().getClass().getCanonicalName();
        Assert.assertEquals(extClassLoaderClassName, "sun.misc.Launcher.ExtClassLoader");

        // 启动类加载器
        // 该环境变量指定的路径下的jar包中的类被启动类加载器所加载,如rt.jar
        System.out.println("bootClassPath为 " + System.getProperty("sun.boot.class.path"));
        ClassLoader bootStrapClassLoader = String.class.getClassLoader();
        Assert.assertEquals("bootStrapClassLoader就用null表示", bootStrapClassLoader, null);


        Launcher launcher = new Launcher();
        System.out.println("Launcher实例的获取类加载器方法getClassLoader(专有方法)返回值为" + launcher.getClassLoader());
        System.out.println("Launcher类的类加载器是" + Launcher.class.getClassLoader());


        System.out.println("ClassLoader.getSystemClassLoader " + ClassLoader.getSystemClassLoader());
    }

    /**
     * 类加载器只会加载直接定义静态字段的类，注意"直接"两字
     */
    @Test
    public void test1() {
        System.out.println(SubClass.ABC);
    }

    @Test
    public void test2() {
        // 只会输出abc
        // 常量在编译阶段会存入调用类的常量池中，也就是说Main类对SubClass1.ABC的引用
        // 已经与SuperClass1无关了，实际上已经转行为Main类对ABC字符串的引用了
        System.out.println(SubClass1.ABC);
    }

    @Test
    public void testAppClassLoader() {
        // 应用类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
    }

    @Test
    public void testClassForName() {
        try {
            // 会报错，重点在看源码
            Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection("localhost:127.0.0.1:3306/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUrlClassLoader() throws MalformedURLException, ClassNotFoundException {
        URL[] urls = {new File("target/classes").toURI().toURL()};
        // 父加载器是AppClassLoader
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        // 因为其有父类加载器AppClassLoader，由于双亲委托模型的存在，该类由AppClassLoader加载
        Class<?> aClass = urlClassLoader.loadClass("com.gson.javajdk.clazzloader.UrlClassLoaderModel");
        System.out.println(aClass.getClassLoader());
    }

    @Test
    public void testUrlClassLoader2() throws MalformedURLException, ClassNotFoundException {
        URL[] urls = {new File("target/classes").toURI().toURL()};
        // 父加载器是null
        URLClassLoader urlClassLoader = new URLClassLoader(urls, null);
        // 因为其无父类加载器，该类则由自身类加载器进行加载
        Class<?> aClass = urlClassLoader.loadClass("com.gson.javajdk.clazzloader.UrlClassLoaderModel");
        System.out.println(aClass.getClassLoader());
    }

}

