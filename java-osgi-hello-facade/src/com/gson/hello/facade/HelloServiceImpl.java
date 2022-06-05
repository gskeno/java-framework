package com.gson.hello.facade;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        System.out.println("HelloServiceImpl say hello");
        return "Say Hello";
    }
}
