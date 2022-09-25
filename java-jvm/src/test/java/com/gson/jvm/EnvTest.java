package com.gsonkeno.jdk.training.jvm;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Properties;

/**
 * Created by gaosong on 2017-12-28
 */
public class EnvTest {
    public static void main(String[] args) {
        Map<String, String> getenv = System.getenv();
        for(Map.Entry entry : getenv.entrySet()){
            System.out.println(entry);
        }

        System.out.println(getenv.get("JAVA_HOME")); //getEnv()获取系统环境变量
        System.out.println(getenv.get("CLASSPATH")); //getEnv()获取系统环境变量
        System.out.println(getenv.get("PATH")); //getEnv()获取系统环境变量

        Properties properties = System.getProperties(); //getProperties()获取jvm系统变量
        for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
            System.out.println(objectEntry);
        }

        System.out.println(properties.get("path.separator"));
        System.out.println(properties.get("os.version"));
        System.out.println("java.class.path->" +  properties.get("java.class.path"));
        System.out.println("java.library.path->" +  properties.get("java.library.path"));
        System.out.println("sun.boot.class.path->" + properties.get("sun.boot.class.path"));
        System.out.println("Path->" +  getenv.get("Path"));

        try {
            URL url = new URL("");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
