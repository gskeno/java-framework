package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

import java.util.List;
import java.util.function.Supplier;

public class MultiProxyInvocation implements Invocation{
    private List<Interceptor> interceptors;

    private Supplier originalCall;

    private int index = 0;

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public void setOriginalCall(Supplier originalCall) {
        this.originalCall = originalCall;
    }

    @Override
    public Object forward() {
        // 所有拦截器都已执行完毕，则执行原始被代理方法，返回即可
        if (index == interceptors.size()){
            return originalCall.get();
        }
        Interceptor interceptor = interceptors.get(index++);
        // interceptor的invoke调用，又会反过来调用invocation.forward，同时invocation中的链条节点也向前走了一步
        return interceptor.invoke(this);
    }
}
