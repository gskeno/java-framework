package com.gson.jackson.model;

import java.net.URL;

public class Main {

    public static void main(String[] args) {
        URL resource = Main.class.getResource("/school.json");
        System.out.println(resource.getPath());

    }
}
