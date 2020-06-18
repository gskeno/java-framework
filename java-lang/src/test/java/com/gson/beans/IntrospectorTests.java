package com.gson.beans;

import com.gson.lang.Item;
import org.junit.Assert;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class IntrospectorTests {
    @Test
    public void test() throws IntrospectionException {
        Class clazz = Item.class;
        //该类的全部属性(实例属性&类属性)，不包含继承的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            String fieldName = declaredField.getName();
            Assert.assertTrue(fieldName.equals("position")
                    || fieldName.equals("years")
                    || fieldName.equals("id")
                    || fieldName.equals("name")
                    || fieldName.equals("desc")
                    || fieldName.equals("height")
                    || fieldName.equals("weight"));
        }

        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        //获取所有的属性描述,包括继承的属性(类属性除外，set,get方法全无的属性除外)
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        System.out.println(Arrays.toString(pds));

        for (int i = 0; i < pds.length; i++) {
            PropertyDescriptor pd = pds[i];
            String name = pd.getName();
            Assert.assertTrue(name.equals("class")
            ||name.equals("desc")
            ||name.equals("height")
            ||name.equals("id")
            ||name.equals("name")
            ||name.equals("weight"));

            if (name.equals("class")){
                Class<?> propertyType = pd.getPropertyType();
                Assert.assertTrue(propertyType == Class.class);
            }
            if (name.equals("desc")){
                Class<?> propertyType = pd.getPropertyType();
                Assert.assertTrue(propertyType == String.class);
            }
            //注意property区分 原生类型和封装类型
            if (name.equals("height")){
                Class<?> propertyType = pd.getPropertyType();
                Assert.assertFalse( propertyType == Long.class);
                // long.class = Long.TYPE https://stackoverflow.com/questions/21464169/difference-between-long-class-and-long-type
                Assert.assertTrue( propertyType == long.class);
                Assert.assertTrue( propertyType == Long.TYPE);

            }
        }

    }
}
