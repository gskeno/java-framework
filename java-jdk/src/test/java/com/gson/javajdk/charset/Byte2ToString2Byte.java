package com.gson.javajdk.charset;


import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Byte2ToString2Byte {
    // https://blog.csdn.net/gs_albb/article/details/117826954
    // 编码指将字符变为字节数组；解码指将字节数组变为字符
    public static void main(String[] args) {
        byte[] b = new byte[]{1, -1, 2, -2
                , 3, -3
        };

        // debug进去，发现这里是解码，s是4个字符
        // [, �, , �]
        String s = new String(b);
        // 这里再进行编码
        // �是无法进行编码的，结果为统一的代号 -17, -65, -67
        byte[] bytes = s.getBytes();
        System.out.println(Arrays.toString(bytes));

        System.out.println(Arrays.toString(new String(b, Charset.forName("ISO-8859-1")).getBytes(Charset.forName("ISO-8859-1"))));
        System.out.println(Arrays.toString(new String(b, StandardCharsets.ISO_8859_1).
                getBytes(StandardCharsets.UTF_8)));
        System.out.println(Arrays.toString(new String(b, StandardCharsets.UTF_8).
                getBytes(StandardCharsets.ISO_8859_1)));
        System.out.println(Arrays.toString(new String(b, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void testASCII(){
        String Aa = new String("Aa");
        byte[] bytes = Aa.getBytes();
        System.out.println(Arrays.toString(bytes));

    }
}
