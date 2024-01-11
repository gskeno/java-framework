package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

public class JuniorCoach implements Interceptor{
    @Override
    public Object invoke(Invocation invocation) {
        System.out.println("想学习狗刨吗？我是初级教练，可以教你");
        Object result = invocation.forward();
        System.out.println("狗刨报名费100");
        return result;
    }
}
