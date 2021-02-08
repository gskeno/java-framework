package com.gson.javajdk.reflect;

class Student implements People{

    @Override
    public People work() {
        System.out.println("学生在听讲");
        return this;
    }

}
