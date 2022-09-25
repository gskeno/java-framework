package com.gsonkeno.pattern.proxy;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by gaosong on 2017-11-13
 */
public class CgLibDBQueryInterceptor implements MethodInterceptor {
    private  IDBQuery query;
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (query == null) query = new DBQuey();
        return query.request();
    }
}
