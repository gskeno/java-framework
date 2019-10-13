package com.gsonkeno.springmvcweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MockController {

    @RequestMapping(value = "/ok")
    public Map<String,Object> ok(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","ok");

        return map;
    }
}
