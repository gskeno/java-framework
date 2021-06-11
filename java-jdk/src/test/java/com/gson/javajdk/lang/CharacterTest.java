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

    @Test
    public  void testChar(){
        char[] chs = {'\u2764','\u2602','\u2600','\u262F','\u262D','\u2622','\u260E'};
        System.out.println(chs);
        //4个字节
        char[] chss = {'\uD83D', '\uDE00'};
        System.out.println(chss);

        char a = 0xFFDD;
        char b = 0xFFDC;
        int c = 0xFFDD;
        System.out.println(c);
        System.out.println((int)a);
        System.out.println(a-b);
    }
}
