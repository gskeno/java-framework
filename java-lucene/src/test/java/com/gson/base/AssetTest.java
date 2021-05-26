package com.gson.base;

public class AssetTest {
    public static void main(String[] args) {
        boolean a = false;
        // vm option 开启-ea才生效
        assert a: "unexpected";
        System.out.println("1");
    }
}
