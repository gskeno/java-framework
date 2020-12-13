package com.gson.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class StringTests {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String chinaWords = new String("中国".getBytes("GBK"),
                "UTF-8");
        System.out.println(chinaWords);
    }

    /**
     * 向左填充
     */
    @Test
    public void testLeftPad(){
        String abc = StringUtils.leftPad("abc", 10, "*");
        System.out.println(abc);
    }

    @Test
    public void testDot(){
        String s= ".7";
        int dotIndex = s.indexOf(".");
        Assert.assertEquals(dotIndex, 0);

        String minPart = s.substring(0, dotIndex);
        Assert.assertEquals(minPart, "");
    }
}
