package com.gson.mockito;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class InstanceMockTest {

    @Mock
    private Random random;

    @Test
    public void test(){
        Mockito.when(random.nextInt()).thenReturn(1,2,3,4);
        Assert.assertTrue(random.nextInt() == 1);
        Assert.assertTrue(random.nextInt() == 2);
        Assert.assertTrue(random.nextInt() == 3);
        Assert.assertTrue(random.nextInt() == 4);
    }
}
