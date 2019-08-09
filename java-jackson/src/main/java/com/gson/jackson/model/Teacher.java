package com.gson.jackson.model;

import lombok.Data;

@Data
public class Teacher {
    private String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}
