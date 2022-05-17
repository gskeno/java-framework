package com.gson.jmockit.tutorial.web;

public class WebOrdinaryBean {

    public String describe(){
        return "WebOrdinaryBean";
    }

    public  final String finalMethod(){
        return "final";
    }

    private String privateMethod(){
        return "private";
    }

    public String getPrivateMethodReturnVal(){
        return privateMethod();
    }
}
