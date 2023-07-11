package com.gson.jvm;


import java.util.ArrayList;
import java.util.List;

public class BigHeap {

    public static void main(String[] args) throws InterruptedException {
        List<Byte[]> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Byte[] b = new Byte[1024 * 1024 * 1024];
            list.add(b);
            System.out.println("共申请" + (i+1) + "g");
            Thread.sleep(5000);
        }

        System.out.println("hello world");
    }
}
