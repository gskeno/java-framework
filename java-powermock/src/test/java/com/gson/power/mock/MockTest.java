package com.gson.power.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class MockTest {

    @Mock
    private CacheService cacheService;

    @Test
    public void test(){
        System.out.println("hello world");
    }
}
