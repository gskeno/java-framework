package com.gsonkeno.junit;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;

public class AssertTest {

    @Test
    public void testAssertThat(){
        Assert.assertThat("abc", both(containsString("a")).and(containsString("b")));
    }
}
