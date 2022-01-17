package com.gson.javajdk.compare;

import com.gson.javajdk.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompareTest {

    private List<Student> studentList;

    @Before
    public void initStudents() {
        studentList = new ArrayList<>();

        studentList.add(new Student(5, null));
        studentList.add(new Student(2, null));
        studentList.add(new Student(1, "A"));
        studentList.add(new Student(0, "a"));
        studentList.add(new Student(3, "B"));
        studentList.add(new Student(4, "d"));
    }

    @Test
    public void testOrder(){

        // 第一个参数是个Function函数接口，要返回要比较排序的字段，这里要根据stdudent.name比较排序
        // 第二个参数是个Comparator接口， 是比较器，定义了比较两个对象进行排序的方法，
        // 这里表示根据name自然顺序排序，如果name为空，排在前面
        Comparator c = Comparator.comparing(Student::getName, Comparator.nullsFirst(Comparator.naturalOrder()));
        studentList.sort(c);
        Assert.assertNull(studentList.get(0).getName());
        Assert.assertEquals(studentList.get(0).getId(), Integer.valueOf(5));
        Assert.assertNull(studentList.get(1).getName());
        Assert.assertEquals(studentList.get(1).getId(), Integer.valueOf(2));

        boolean occurNpe = false;
        try {
            // comparing 一定要设置第二个参数，否则如果name为空，会抛出空指针
            c = Comparator.comparing(Student::getName);
            studentList.sort(c);
            System.out.println(studentList);
        }catch (NullPointerException e){
            occurNpe = true;
        }
        assert  occurNpe;
    }
}
