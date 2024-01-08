package com.gson.javajdk.proxyjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * @author gaosong
 * @since 2019-06-18
 */
public class ProxyTicketCenter implements InvocationHandler {
    RealTicketCenter realTicketCenter;

    public ProxyTicketCenter(RealTicketCenter realTicketCenter) {
        this.realTicketCenter = realTicketCenter;
    }

    /**
     *
     * @param proxy 真正的代理类$Proxy0, extends Proxy implements TicketCenter
     * @param method 代理的方法
     * @param args 执行方法时的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        Object object = null;
        object=method.invoke(realTicketCenter,args);
        System.out.println("after");
        return object;
    }
}
