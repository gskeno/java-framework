package com.gson.javajdk.clazz;

import org.junit.Test;

import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.LinkedList;
import java.util.List;

public class ClazzTest {
    @Test
    public void test(){
        Class<?> clazz = LinkedList.class;
        Class<? extends List> aClass = clazz.asSubclass(List.class);

        System.out.println(aClass);
    }

    @Test
    public void testCast(){
        List<String> a= new LinkedList<>();
        a.add("1");

        Object b = a;
        /**
         * 将对象转化为某特定类
         */
        List cast = List.class.cast(b);
        System.out.println(cast);

        cast = (List)a;
        System.out.println(cast);
    }

    @Test
    public void testGetDeclaredField() throws NoSuchFieldException, IllegalAccessException {
        Field value = String.class.getDeclaredField("value");
        System.out.println(value);
        String abc = new String("abc");
        try{
            value.get(String.class);
            throw new UnexpectedException("not run here");
        }catch (Exception e){
            e.printStackTrace();
            assert  e instanceof IllegalAccessException;
        }

        int[] ints = new int[5];
        // 数组是没有DeclaredField的
        Field[] declaredFields = ints.getClass().getDeclaredFields();
        for(Field field : declaredFields){
            System.out.println(field);
        }
    }

    @Test
    public void testGetDeclaredField1() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(WholeObject.class.getDeclaredField("a"));
        System.out.println(WholeObject.class.getDeclaredField("b"));
        System.out.println(WholeObject.class.getDeclaredField("c"));
        System.out.println(WholeObject.class.getDeclaredField("d"));
        System.out.println(WholeObject.class.getDeclaredField("e"));
        System.out.println(WholeObject.class.getDeclaredField("f"));

        Field f = WholeObject.class.getDeclaredField("f");
        System.out.println(f.get(WholeObject.class));
        System.out.println(f.get(new WholeObject()));
        System.out.println(f.get(new WholeObject()));
    }
}
