package com.gsonkeno.pattern.proxy;


import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created by gaosong on 2017-11-13
 */
public class Main {
    public static void main(String[] args) {
        //创建代理类
        DBQueryProxy proxy = new DBQueryProxy();
        //在使用时才创建真实对象
        System.out.println(proxy.request());

        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IDBQuery.class}, new JdkDBQueryProxy());
        System.out.println(jdkProxy.request());

        //cglib生成动态代理
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CgLibDBQueryInterceptor()); //指定切入器,一个实现MethodInterceptor接口的类
        enhancer.setInterfaces(new Class[]{IDBQuery.class}); //指定实现的接口
        //enhancer.setSuperclass();指定实现的父类
       //生成代理类的实例(没有接口限制，此处只不过恰好是接口而已,如果是普通类，则代理类实质上是普通类的子类)
        IDBQuery cglibProxy = (IDBQuery) enhancer.create(); //生成代理类实例
        System.out.println(cglibProxy.getClass().getName());
        System.out.println(cglibProxy.request());
    }
}
