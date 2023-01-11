package com.gson.javajdk.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropertiesTest {

    @Test
    public void test() throws IOException {
        String config = "A=";
        StringReader reader=new StringReader(config);
        Properties property=new Properties();
        property.load(reader);

        Object a = property.get("A");
        Assert.assertTrue(a.equals(""));
    }
}
