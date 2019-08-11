package com.gson.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gson.jackson.model.School;
import com.gson.jackson.model.Teacher;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY;

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

    /**
     * mapper支持的特性
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        // 可以读到测试资源目录下的文件；也可以读到正式资源目录下的文件
        URL resource = this.getClass().getResource("/testschool.json");
        String content = IOUtils.toString(resource, "utf-8");

        ObjectMapper om = new ObjectMapper();

        // 字符串转化为Tree Json时，允许有相同的key
        om.enable(FAIL_ON_READING_DUP_TREE_KEY);
        // 字符串转化为Tree Json时，不允许有相同的key，与上面相反
        om.configure(FAIL_ON_READING_DUP_TREE_KEY,true);

        JsonNode jsonNode = om.readTree(content);

    }
}
