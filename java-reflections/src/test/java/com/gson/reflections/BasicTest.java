package com.gson.reflections;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * https://www.yisu.com/zixun/581493.html
 */
public class BasicTest {

    @Test
    public void test(){
        // class FooList extends ArrayList<Foo>
        FooList foos = new FooList();
        Type superClass = foos.getClass().getGenericSuperclass();
        // return class Foo
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        Assert.assertEquals(type, Foo.class);

        // 普通类的父范型类是Object
        Type genericSuperclass = new Foo().getClass().getGenericSuperclass();
        Assert.assertTrue(genericSuperclass.equals(Object.class));

        // Object，Interface, Primitive, Void 父泛型类为null
        Assert.assertNull(void.class.getGenericSuperclass());
        Assert.assertNull(int.class.getGenericSuperclass());
        Assert.assertNull(List.class.getGenericSuperclass());
        Assert.assertNull(Object.class.getGenericSuperclass());
    }
}
