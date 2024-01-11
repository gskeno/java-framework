package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

public class IntermediateCoach implements Interceptor{
    @Override
    public Object invoke(Invocation invocation) {
        System.out.println("想学习蛙泳吗？我是中级教练，可以教你");
        Object result = invocation.forward();
        System.out.println("蛙泳报名费200");
        return result;
    }
}
