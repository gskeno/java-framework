package com.gson.pico.container;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorTest {
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Foo> clazz = Foo.class;
        Constructor<Foo> constructor = clazz.getConstructor(null);
        Foo foo = constructor.newInstance(null);
    }
}
