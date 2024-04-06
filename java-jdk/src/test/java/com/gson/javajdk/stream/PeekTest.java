package com.gson.javajdk.stream;

import org.junit.Test;

import java.util.stream.Stream;

public class PeekTest {

    /**
     * peek操作不会改变流元素
     */
    @Test
    public void testPeak(){
        Stream.of(1, 2, 3)
                .peek(e -> e=e+1)
                .forEach(e-> System.out.println("peek unModified"+e));
    }

    /**
     * map操作会改变流元素
     */
    @Test
    public void testMap(){
        Stream.of(1, 2, 3)
                .map(e -> e=e+1)
                .forEach(e-> System.out.println("map modified"+e));
    }

    @Test
    public void testPeekNotExecute(){
        Stream.of(1, 2, 3)
                .peek(e -> {
                    System.out.println("peekNotExecute" + e);
                })
                .count();
    }
}
