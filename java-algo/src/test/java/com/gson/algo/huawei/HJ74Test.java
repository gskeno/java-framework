package com.gson.algo.huawei;

import org.junit.Test;

public class HJ74Test {
    @Test
    public void test(){
        HJ74参数解析.analysis("xcopy /s c:\\\\ d:\\\\e");
    }

    @Test
    public void test0(){
        HJ74参数解析.analysis("xcopy /s \"C:\\\\program files\" \"d:\\\"");
    }

    @Test
    public void test1(){
        HJ74参数解析.analysis("xcopy /s \"C:\\\\program files\" \"d:\\\"");
    }

    @Test
    public void test2(){
        String s = "d:\\e";
        for(char c : s.toCharArray()){
            System.out.print(c);
        }
    }

    @Test
    public void test3(){
        String  s = "\"c9\"";
        for(char c : s.toCharArray()){
            System.out.println(c);
            System.out.println(c=='"');
        }
    }
}
