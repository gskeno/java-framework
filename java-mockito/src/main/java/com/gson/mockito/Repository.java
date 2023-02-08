package com.gson.mockito;

import java.util.UUID;

public class Repository {

    public String getID(){
        System.out.println("getID method invoked");
        return UUID.randomUUID().toString();
    }

    public String getPrefixStudent(String prefix){
        System.out.println("getPrefixStudent method invoked");
        return prefix + UUID.randomUUID().toString();
    }
}
