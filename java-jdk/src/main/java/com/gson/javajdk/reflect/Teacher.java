package com.gson.javajdk.reflect;

class Teacher implements People{

    @Override
    public People work() {
        System.out.println("老师在讲课");
        return this;
    }
}
