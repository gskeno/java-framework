package com.gson.spring.method_replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by gaosong on 2018-04-03
 */
public class TestMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("changeMe")){
            System.out.println("我替换了原有的方法");
            return null;
        }else if (method.getName().equals("getCount")){
            System.out.println("getCount被替换返回2");
            return 2;
        }
        return null;

    }
}
