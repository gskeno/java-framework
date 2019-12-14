package com.gson.fastjson;

import java.io.Serializable;

public class Member implements Serializable {

    private static final long serialVersionUID = 2298239329565440887L;

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
