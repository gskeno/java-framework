package com.gson.javajdk.nio;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建一个客户端Socket连接服务器
            Socket clientSocket = new Socket("localhost", 8080);

            // 创建一个大小为4字节的ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(4);


            // 切换为网络字节序（大端序）
            buffer.order(ByteOrder.LITTLE_ENDIAN);

            // 存入一个int类型的数据
            buffer.putInt(123456789);


            // 写入Socket输出流
            clientSocket.getOutputStream().write(buffer.array());

            // 关闭Socket连接
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
