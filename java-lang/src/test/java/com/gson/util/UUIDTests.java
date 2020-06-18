package com.gson.util;

import java.util.UUID;

public class UUIDTests {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
