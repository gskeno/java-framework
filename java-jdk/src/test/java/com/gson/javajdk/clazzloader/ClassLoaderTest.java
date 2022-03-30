package com.gson.javajdk.clazzloader;

import sun.misc.Launcher;
import sun.net.spi.nameservice.dns.DNSNameService;

public class ClassLoaderTest {

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

    }
}
