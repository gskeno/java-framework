package com.gson.jmockit.tutorial;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class MockupTest {
    Calendar calendar = Calendar.getInstance();
    @Test
    public void test(){
        new MockUp<Calendar>(Calendar.class){
            @Mock
            public int get(int field)
            {
                if (field == Calendar.HOUR){
                    return 1;
                }
                // return 0表示其他field值为其他值时，返回0
                return 0;
            }
        };

        Assert.assertEquals(calendar.get(Calendar.HOUR), 1);
        Assert.assertEquals(calendar.get(Calendar.YEAR), 0);
    }
}
