package com.gson.design_pattern.proxy.jdk_multi_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MotherWarn implements InvocationHandler {
    private final Travel travel;

    public MotherWarn(Travel travel){
        this.travel = travel;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("生活苦不苦？儿子");
        Object result = method.invoke(travel, args);
        System.out.println("生活苦，老娘给你寄好吃好喝的");
        return result;
    }
}
