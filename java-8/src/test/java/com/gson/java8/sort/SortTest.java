package com.gson.java8.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    @Test
    public void testSOrt(){
        List<Record> list = new ArrayList<>();

        Record record1 = new Record();
        record1.setTime(1L);
        Record record2 = new Record();
        record2.setTime(2L);

        list.add(record1);
        list.add(record2);

        //倒排
        list.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });

        System.out.println(list);

    }
}
