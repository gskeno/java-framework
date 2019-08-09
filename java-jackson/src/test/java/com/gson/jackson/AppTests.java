package com.gson.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gson.jackson.model.Main;
import com.gson.jackson.model.School;
import com.gson.jackson.model.Teacher;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class AppTests {
    @Test
    public void test() throws IOException {
        // 获取资源方式1
         URL resource = this.getClass().getResource("/school.json");

        // 获取资源方式2
        // URL resource = this.getClass().getClassLoader().getResource("school.json");
        String content = IOUtils.toString(resource, "utf-8");
        System.out.println(content);

        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(content);

        School school = om.treeToValue(jsonNode, School.class);
        System.out.println(school);

        System.out.println("----------------------------");
        Teacher teacher = new Teacher();
        teacher.setName("yangsheng");
        school.setTeachers(Arrays.asList(teacher));

        System.out.println(om.writeValueAsString(school));
    }
}
