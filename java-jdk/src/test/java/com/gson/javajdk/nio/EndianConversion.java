package com.gson.javajdk.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndianConversion {

    public static void main(String[] args) {
        // 创建一个大小为4字节的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(4);
        // 默认是大端序, BIG_ENDIAN
        System.out.println(buffer.order());
        // 存入一个int类型的数据
        buffer.putInt(123456789);
        // 75bcd15
        System.out.println(Integer.toHexString(123456789));

        // 16进制大端序表达的7 5b cd 15, 如果用小端序来表达就是 15->cd->5b->07，即15cd5b07
        System.out.println(Integer.valueOf("15cd5b07", 16));

        // 切换为小端序
         buffer.order(ByteOrder.LITTLE_ENDIAN);

        // 读取转换后的数据
        int value = buffer.getInt(0);
        System.out.println(value);
    }
}
