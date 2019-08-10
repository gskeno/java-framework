package com.gson.jackson.polymorphic;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.Set;

/**
 * json字符串转化为对象时的多态处置方案 @JsonTypeInfo
 */
public class PageTest {

    @Test
    public void test() throws IOException {
        Reflections reflections = new Reflections();

        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(JsonTypeInfo.class);

        ObjectMapper  mapper   = new ObjectMapper();
        // 注册方式1
        //classSet.parallelStream().forEach(clazz -> mapper.registerSubtypes(clazz));

        // 注册方式2
        classSet = reflections.getTypesAnnotatedWith(JsonTypeName.class);
        classSet.parallelStream().forEach(clazz -> mapper.registerSubtypes(new NamedType(clazz, clazz.getAnnotation(JsonTypeName.class).value())));

        String numberJson = " {\n" +
                "        \"type\": \"number\",\n" +
                "        \"label\": \"价格\",\n" +
                "        \"uiType\": \"input\",\n" +
                "        \"number\" : 110\n" +
                "        \n" +
                "      }";
        NumberPage page = (NumberPage)mapper.readValue(numberJson, Page.class);
        System.out.println(page); // NumberPage(number=110) ,父类的属性是私有的，所以toString没有打印
        System.out.println(page.getLabel()); // 但父类的方法是公开的，所以可以拿到父类属性

    }
}
