package com.gsonkeno.springmvcweb.controller;

import javax.annotation.Resource;

public class SimpleService {

    @Resource
    private BaseDaoController baseDaoController;

    public void doService(){
        System.out.println("SimpleService doService");
    }
}
