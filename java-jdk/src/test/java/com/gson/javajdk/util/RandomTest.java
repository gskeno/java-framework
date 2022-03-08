package com.gson.javajdk.util;

import org.junit.Test;

import java.util.Random;

public class RandomTest {
    @Test
    public void testRandom(){
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            // 结果 [0, bound)
            int result = random.nextInt(1);
            assert result == 0;
        }
    }
}
