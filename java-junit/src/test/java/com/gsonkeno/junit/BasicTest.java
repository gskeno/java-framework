package com.gsonkeno.junit;

import org.junit.Test;

public class BasicTest {

    @Test
    public void testHello(){
        System.out.println("hello");
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void testBye(){
        System.out.println("bye");
        System.out.println(Thread.currentThread().getName());
    }
}
