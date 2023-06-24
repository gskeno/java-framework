package com.gson.javajdk.file;

import java.io.*;

/**
 * 写文件时发生了删除
 */
public class WriteFileWhileDeleting {
    public static void main(String[] args) throws IOException, InterruptedException {
       read();
    }

    public static void write() throws IOException, InterruptedException {
        File file = new File("java-jdk/WriteFileWhileDeleting.txt");
        System.out.println(file.getAbsolutePath());

        FileWriter fileWriter = new FileWriter(file, true);
        for (int i = 0; i < 26; i++) {
            fileWriter.write('a' + i);
            Thread.sleep(1000);
            fileWriter.flush();
        }
        fileWriter.close();
    }
    public static void read() throws IOException, InterruptedException {
        File file = new File("java-jdk/WriteFileWhileDeleting.txt");
        System.out.println(file.getAbsolutePath());

        FileReader fileReader = new FileReader(file);
        int n = -1;
        while ( (n = fileReader.read()) != -1){
            System.out.println(n);
            Thread.sleep(1000);
        }
    }
}
