package com.gson.javaslf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderTests {
    public static final String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    public static final String HELLO_WORLD_PATH = "com/gson/javaslf4j/HelloWorld.class";
    public static void main(String[] args) {
        try {
            Logger logger = LoggerFactory.getLogger(ClassLoaderTests.class);
            logger.info("Hello World");
            Enumeration<URL> resources = ClassLoaderTests.class.getClassLoader().getResources(STATIC_LOGGER_BINDER_PATH);
            while (resources.hasMoreElements()){
                System.out.println(resources.nextElement());
            }

            resources = ClassLoaderTests.class.getClassLoader().getResources(HELLO_WORLD_PATH);
            while (resources.hasMoreElements()){
                System.out.println(resources.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
