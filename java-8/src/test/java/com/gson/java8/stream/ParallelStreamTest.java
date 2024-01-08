package com.gson.java8.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ParallelStreamTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int total = 1000;
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            list.add(new String(random.nextInt(2^20) + ""));
        }
        List<String> copyList = new ArrayList<>();
        List<String> copyList2 = Collections.synchronizedList(new ArrayList<>());
        list.parallelStream().forEach((element)->{
            copyList.add(element + "-copy");
            copyList2.add(element + "-copy");
        });
        System.out.println("copyListSize=" + copyList.size());
        System.out.println("copyListSize=" + copyList2.size());
    }
}
