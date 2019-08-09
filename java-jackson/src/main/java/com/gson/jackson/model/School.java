package com.gson.jackson.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class School {

    private HeadMaster headMaster;

    /**
     * @JsonIgnore注解会使序列化和反序列化时该字段都被忽略掉
     * 序列化：  对象转json字符串
     * 反序列化：字符串转对象
     */
    @JsonIgnore
    private List<Teacher> teachers;

    private List<Student> students;

    @Override
    public String toString() {
        return "School{" +
                "headMaster=" + headMaster +
                ", teachers=" + teachers +
                ", students=" + students +
                '}';
    }
}
