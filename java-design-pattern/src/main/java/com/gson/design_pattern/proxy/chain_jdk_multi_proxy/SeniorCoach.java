package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

public class SeniorCoach implements Interceptor{
    @Override
    public Object invoke(Invocation invocation) {
        System.out.println("想学习自由泳吗？我是高级教练，可以教你");
        Object result = invocation.forward();
        System.out.println("自由泳报名费300");
        return result;
    }
}
