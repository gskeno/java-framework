package com.gson.javajdk.reflect;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        People people = new Student();
        PeopleInvocationHandler peopleInvocationHandler = new PeopleInvocationHandler(people);
        //People peopleProxy= (People)Proxy.newProxyInstance(Student.class.getClassLoader(), Student.class.getInterfaces(), peopleInvocationHandler);
        People peopleProxy= (People) Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), peopleInvocationHandler);

        peopleProxy.work();
    }
}
