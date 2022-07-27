package com.gson.javajdk.memory;

import com.gson.javajdk.model.Student;

public class D {

    public static final Student  student = new Student();

    public static void changeStudent(){
        // final修饰的类静态属性叫做常量，在方法区中，不能再重新赋值，如下
        // 但是可以修改student的内部属性
        // student = new Student(); X
        student.setId(2);
    }


}
