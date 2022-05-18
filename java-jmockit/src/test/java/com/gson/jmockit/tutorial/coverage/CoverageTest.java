package com.gson.jmockit.tutorial.coverage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.Assert;

import java.util.List;

public class CoverageTest {
    ISayHello sayHello = new SayHello();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //测试 sayHello(String who, int gender);
    @Test
    public void testSayHello1() {
        Assert.isTrue(sayHello.sayHello("david", ISayHello.MALE).equals("hello Mr david"));
        Assert.isTrue(sayHello.sayHello("lucy", ISayHello.FEMALE).equals("hello Mrs lucy"));
        thrown.expect(IllegalArgumentException.class);
        sayHello.sayHello("david", 3);
    }
    //测试 sayHello(String[] who, int[] gender)
    @Test
    public void testSayHello2() {
        String[] who = new String[] { "david", "lucy" };
        int[] gender = new int[] { ISayHello.MALE, ISayHello.FEMALE };
        List<String> result = sayHello.sayHello(who, gender);
        Assert.isTrue(result.get(0).equals("hello Mr david"));
        Assert.isTrue(result.get(1).equals("hello Mrs lucy"));
    }
}
