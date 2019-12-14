package com.gson.springaop.services;

import org.springframework.stereotype.Component;

@Component
public class ReadService {

    public String read(String message){
        return message + "_read";
    }
}
