package com.gson.design_pattern.proxy;


import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created by gaosong on 2017-11-13
 */
public class Main {

    private static void jdkProxy(){
        //创建代理类
        DBQueryProxy proxy = new DBQueryProxy();
        //在使用时才创建真实对象
        System.out.println(proxy.request());

        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IDBQuery.class}, new JdkDBQueryProxy());
        System.out.println(jdkProxy.request());
    }

    private static void cglibProxy(){
        //cglib生成动态代理，本地会生成动态代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
        Enhancer enhancer = new Enhancer();
        //指定切入器,一个实现MethodInterceptor接口的类,此步骤必不可少
        enhancer.setCallback(new CgLibDBQueryInterceptor());
        //可代理接口
        enhancer.setInterfaces(new Class[]{IDBQuery.class});
        //也可代理一个普通类
        // enhancer.setSuperclass(DBQuey.class);

        //生成代理类的实例(没有接口限制，此处只不过恰好是接口而已,如果是普通类，则代理类实质上是普通类的子类)
        //生成代理类实例，范式使用
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        System.out.println("代理类类名 :" +cglibProxy.getClass().getName());
        System.out.println(cglibProxy.request());
    }
    public static void main(String[] args) {
        cglibProxy();
    }
}
