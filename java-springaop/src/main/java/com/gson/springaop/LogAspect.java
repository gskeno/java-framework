package com.gson.springaop;

public class LogAspect {

    public void afterReturning(Object result) {
        System.out.println(result);
    }
}
