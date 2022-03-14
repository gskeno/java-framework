package com.gson.springaop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadService {

    @Autowired
    private WriteService writeService;

    public String read(String message){
        writeService.write("xiix");
        throw new RuntimeException("write 后出现了 异常");
        //return message + "_read";
    }

    public String read1(){
        return "read1";
    }
}
