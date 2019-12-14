package com.gson.springaop.services;

import org.springframework.stereotype.Component;

@Component
public class WriteService {
    public String write(String message){
        return message + "_write";
    }
}
