package com.gson.serialize.model;

import java.io.Serializable;

/**
 * @author ruchen
 * @date 2020/6/24
 */
public class Student implements Serializable {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
