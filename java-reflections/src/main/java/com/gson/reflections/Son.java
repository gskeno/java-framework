package com.gson.reflections;

import com.gson.reflections.annotation.After;

public class Son extends Parent {

    @After(key = "getSonName")
    public String getSonName(){
        return "son";
    }
}
