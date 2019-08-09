package com.gson.jackson.model;

import lombok.Data;

/**
 *
 */
@Data
public class Student {
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
