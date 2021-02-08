package com.gson.javajdk.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class  PeopleInvocationHandler implements InvocationHandler {
    private  Object rawObject;

    public PeopleInvocationHandler(Object rawObject){
        this.rawObject = rawObject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(rawObject, args);
        System.out.println("obj=" + obj);
        System.out.println("obj.class=" + obj.getClass());
        System.out.println("rawObj=" + rawObject);
        System.out.println("rawObj.class=" + rawObject.getClass());
        //proxy.toString()会引起递归循环调用
        //System.out.println("proxy=" + proxy);
        System.out.println("proxy.class=" + proxy.getClass());
        return proxy;
    }
}
