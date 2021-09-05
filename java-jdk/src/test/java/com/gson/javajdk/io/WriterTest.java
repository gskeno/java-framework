package com.gson.javajdk.io;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterTest {
    @Test
    public void test() throws IOException {
        String fileName = "write.txt";
        FileOutputStream os = new FileOutputStream(fileName, true);
        final byte[] READ_TEST_BYTES = new byte[] {
                (byte) 0x80, 0x01,
                (byte) 0xFF, 0x7F};

        os.write(READ_TEST_BYTES);
        os.flush();
        os.close();

        FileInputStream is = new FileInputStream(fileName);
        System.out.println(is.read());
        System.out.println(is.read());

    }
}
