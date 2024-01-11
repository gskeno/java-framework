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

    /**
     *
     * @param target 被代理对象，也即目标对象
     */
    public ProxyEntrance(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        // invocation是第一个信息很丰富的调用链的包装者，
        // 内部包含了一个拦截器链，在每个拦截器中用户可以做自己想做的事情，同时推动进入链条的下一个节点
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
