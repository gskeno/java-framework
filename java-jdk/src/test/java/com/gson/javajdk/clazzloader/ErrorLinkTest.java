package com.gson.javajdk.clazzloader;

import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * http://ifeve.com/classloader/
 */
public class ErrorLinkTest {
    @Test
    public void test() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // cl1在加载HandleUtils和Param时将会使用AppClassLoader
        URLClassLoader cl1 = new URLClassLoader(new URL[] {new File("target/classes").toURI().toURL()},
                null) {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                if ("com.gson.javajdk.clazzloader.HandleUtils".equals(name)) {
                    return ClassLoader.getSystemClassLoader().loadClass(name);
                }

                if ("com.gson.javajdk.clazzloader.Param".equals(name)) {
                    return ClassLoader.getSystemClassLoader().loadClass(name);
                }

                return super.loadClass(name);
            }

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                System.out.println("class=" + name + "是用cl1寻找的");
                return super.findClass(name);
            }
        };

        // 这是AppClassLoader加载的param2
        ClassLoader.getSystemClassLoader().loadClass("com.gson.javajdk.clazzloader.Param2");
        HandleUtils hu = (HandleUtils) cl1.loadClass("com.gson.javajdk.clazzloader.HandleUtils").newInstance();
        // 这是cl1加载的param2，会冲突
        hu.m((Param) cl1.loadClass("com.gson.javajdk.clazzloader.Param2").newInstance());
    }
}
