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
                    || fieldName.equals("desc"));
        }

        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        for (int i = 0; i < pds.length; i++) {
            PropertyDescriptor pd = pds[i];
            System.out.println(pd);
            System.out.println(pd.getPropertyType());
        }

    }
}
