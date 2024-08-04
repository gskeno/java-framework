package com.gson.javajdk.nio;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * https://blog.51cto.com/u_16175454/7442234
 * 大端序，小端序
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 创建一个服务器Socket监听端口8080
            ServerSocket serverSocket = new ServerSocket(8080);

            // 接受客户端连接
            Socket clientSocket = serverSocket.accept();

            // 创建一个大小为4字节的ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(4);


            // 从Socket输入流读取数据
            clientSocket.getInputStream().read(buffer.array());

            buffer.order(ByteOrder.LITTLE_ENDIAN);

            // 读取转换后的数据
            int value = buffer.getInt(0);
            System.out.println(value);

            // 关闭Socket连接
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
