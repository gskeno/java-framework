package com.gson.serialize;

import com.gson.serialize.model.Student;
import org.junit.Test;

import java.io.*;

public class JdkSerializeTest {

    //1.Student类要实现Serializable接口
    //2.序列化时会生成文件，不优雅
    @Test
    public void testSerialize() throws IOException, ClassNotFoundException {
        Student student = new Student("张萌");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
        oos.writeObject(student);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.txt"));
        Student student1  = (Student)ois.readObject();
        System.out.println(student1.getName());
    }
}
