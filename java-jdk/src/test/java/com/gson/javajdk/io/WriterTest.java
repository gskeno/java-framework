package com.gson.javajdk.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterTest {
    @Test
    public void test() throws IOException {
        String fileName = "write.txt";
        FileOutputStream os = new FileOutputStream(fileName, true);
        os.write(30);
        os.write(20);
        os.flush();
        os.close();

        FileInputStream is = new FileInputStream(fileName);
        System.out.println(is.read());
        System.out.println(is.read());

    }
}
