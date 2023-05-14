package com.gson.mysql.test;

import org.junit.Test;

public class DomainTest {

    @Test
    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("com.gson.mysql.Domain", false, ClassLoader.getSystemClassLoader());
        System.out.println("----");
        aClass.newInstance();
    }
}
