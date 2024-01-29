package com.gson.googlegson;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.gson.jackson.model.Person;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

public class GsonTest {

    @Test
    public void test(){
        Gson gson = new Gson();
        String json = "[{\"id\":1, \"name\":\"gs\", \"address\":{\"city\":\"河南\", \"cityCode\":\"HN\"}}]";
        // 通过创建的匿名子类，可以获取父类 List<Person>上的范型类，就是Person类
        TypeToken<List<Person>> typeToken = new TypeToken<List<Person>>() {

        };
        /**
         * @see sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
         */
        Type type = typeToken.getType();
        // 会转化为指定泛型Person
        List<Person> people = gson.fromJson(json, type);
        System.out.println(people);

        // 会自动转化为LinkedTreeMap类型
        List list = gson.fromJson(json, List.class);
        System.out.println(list);
    }
}
