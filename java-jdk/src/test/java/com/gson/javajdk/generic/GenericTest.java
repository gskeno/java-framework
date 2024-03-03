package com.gson.javajdk.generic;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericTest {

    @Test
    public void test(){
        // 这里一定要使用匿名子类，子类的泛型信息会被擦除，其父类的信息不会，
        // ArrayList<Map<String,Object>>的信息都可以得到
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(){

        };
        // java.util.ArrayList<java.util.Map<java.lang.String, java.lang.Object>>
        Type listClass = list.getClass().getGenericSuperclass();
        // java.util.Map<java.lang.String, java.lang.Object>
        Type mapType = ((ParameterizedType) listClass).getActualTypeArguments()[0];
        // class java.lang.String
        Type stringType = ((ParameterizedType) mapType).getActualTypeArguments()[0];
        // class java.lang.Object
        Type objectType = ((ParameterizedType) mapType).getActualTypeArguments()[1];
    }
}
