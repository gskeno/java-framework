package com.gson.javajdk.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest extends Assert {
    @Test
    public void test(){
        ThreadLocalRandom current1 = ThreadLocalRandom.current();
        ThreadLocalRandom current2 = ThreadLocalRandom.current();
        assertTrue( current1 == current2);
    }
}
