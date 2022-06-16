package com.gson.paranamer;

import org.gson.poem.Himalaya;
import org.junit.Test;

public class VisitorTest {
    @Test
    public void test(){
        Visitor visitor = new Visitor();
        visitor.visit(new Himalaya());
        System.out.println(Visitor.__PARANAMER_DATA);
    }
}
