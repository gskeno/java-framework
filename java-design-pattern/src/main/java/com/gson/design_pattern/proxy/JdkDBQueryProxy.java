package com.gsonkeno.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by gaosong on 2017-11-13
 */
public class JdkDBQueryProxy implements InvocationHandler {
    private  IDBQuery query;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (query == null) query = new DBQuey();
        return query.request();
    }
}
