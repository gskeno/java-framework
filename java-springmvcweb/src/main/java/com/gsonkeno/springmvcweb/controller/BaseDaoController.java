package com.gsonkeno.springmvcweb.controller;

import com.gson.spring.inner.DaoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseDaoController {
    @Autowired
    private DaoContext daoContext;

    @RequestMapping(value = "/baseDao")
    public String baseDao(){
        String s = daoContext.getBasicDao().toString();
        return s;
    }
}
