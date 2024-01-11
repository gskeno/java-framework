package com.gson.design_pattern.proxy.jdk_multi_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FatherWarn implements InvocationHandler {
    /**
     * 这里使用Object类，是否更通用呢
     */
    private final Travel travel;

    public FatherWarn(Travel travel){
        this.travel = travel;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("缺钱吗？儿子");
        Object result = method.invoke(travel, args);
        System.out.println("缺钱找老子要");
        return result;
    }
}
