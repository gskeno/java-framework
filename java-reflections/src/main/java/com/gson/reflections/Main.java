package com.gson.reflections;

import com.gson.reflections.annotation.After;
import com.gson.reflections.annotation.Before;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .addUrls(ClasspathHelper.forPackage("com.gson.reflections"))
                .addScanners(new ResourcesScanner(), new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner(), new SubTypesScanner());

        Reflections reflections = new Reflections(configurationBuilder);

        // 获取Parent的子类
        Set<Class<? extends Parent>> subClass = reflections.getSubTypesOf(Parent.class);
        System.out.println(subClass);

        // 获取拥有Before注解的类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Before.class);
        System.out.println(typesAnnotatedWith);

        // 获取拥有After注解的方法
        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(After.class);
        System.out.println(methodsAnnotatedWith);


    }
}
