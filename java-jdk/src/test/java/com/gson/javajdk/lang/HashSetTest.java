package com.gson.javajdk.lang;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

    @Test
    public void test(){
        Set<String> stringSet = new HashSet<>();
        stringSet.add(null);
        stringSet.add(null);

        System.out.println(stringSet.size());
    }
}
