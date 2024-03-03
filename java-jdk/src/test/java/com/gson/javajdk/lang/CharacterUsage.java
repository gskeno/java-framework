package com.gson.javajdk.lang;

import org.junit.Test;

public class CharacterUsage {

    @Test
    public void test(){
        String emoji = "ab😠de";
        char[] charArray = emoji.toCharArray();
        int i = Character.codePointAt(charArray, 4, charArray.length);
        System.out.println(i);
    }
}
