package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ProxyEntrance implements InvocationHandler {
    private Object target;

    private List<Interceptor> interceptors = new ArrayList<>();


    public void addInterceptor(Interceptor interceptor){
        interceptors.add(interceptor);
    }

    public ProxyEntrance(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MultiProxyInvocation invocation = new MultiProxyInvocation();
        invocation.setInterceptors(interceptors);
        invocation.setOriginalCall(()->{
            try {
                return method.invoke(target, args);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        Object result = invocation.forward();
        return result;
    }
}
