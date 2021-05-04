package com.gson.javajdk.lang;

import org.junit.Test;

public class CharacterTest {

    @Test
    public void test(){
        char[] chars = {'a','b','c', 'd','c','e'};
        int i = Character.codePointAt(chars, 2, 6);
        System.out.println(i);

        char[] cChars = {'我','是','中', '国', '人', '民'};
        i = Character.codePointAt(cChars, 2, 6);
        System.out.println(i);
    }
}
