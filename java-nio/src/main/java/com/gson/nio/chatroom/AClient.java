package com.gson.nio.chatroom;

import com.gson.nio.NioClient;

import java.io.IOException;

public class AClient {

    public static void main(String[] args)
            throws IOException {
        new NioClient().start("AClient");
    }

}
