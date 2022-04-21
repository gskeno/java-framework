package com.gson.javajdk.lang;

import org.junit.Test;

public class StringBuilderTest {

    /**
     * 空sb，不报错，不做任何变化
     */
    @Test
    public void testDelete(){
        StringBuilder sb = new StringBuilder();
        sb.delete(0, sb.length());
        System.out.println(sb);
    }

    /**
     * 4-8之间的字符被删除，后面的字符相应的向前移动
     */
    @Test
    public void testDelete5To10(){
        StringBuilder sb = new StringBuilder();
        sb.append("abcdefghijklmn");
        sb.delete(4,8);
        System.out.println(sb);
    }


}
