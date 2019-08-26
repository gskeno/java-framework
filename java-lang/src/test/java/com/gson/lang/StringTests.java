package com.gson.lang;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class StringTests {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String chinaWords = new String("中国".getBytes("GBK"),
                "UTF-8");
        System.out.println(chinaWords);
    }
}
