package com.gson.java8.stream;

import com.gson.java8.model.Clazz;
import com.gson.java8.model.School;
import com.gson.java8.model.Student;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
/**
 * 正确使用Optional类
 * @date 2019/12/28
 */
public class OptionalTests {
    @Test
    public void testOptional(){
        School school = new School();
        // Option正确的使用方式
        String studentName = Optional.ofNullable(school).map(School::getClazz)
                .map(Clazz::getStudent).map(Student::getName).orElse("没有学生");

        System.out.println(studentName);


        List<String> a = null;
        String s = Optional.ofNullable(a).map(o -> o.size() == 0 ? null : o.get(0)).orElse(null);
        System.out.println(s);
    }
}
