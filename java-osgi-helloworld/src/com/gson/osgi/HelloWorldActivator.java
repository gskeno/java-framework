package com.gson.osgi;

import com.gson.hello.facade.HelloService;
import com.gson.hello.facade.HelloServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class HelloWorldActivator implements BundleActivator {
    ServiceReference helloServiceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Hello, World!");
//        HelloService facade = new HelloServiceImpl();
//        facade.sayHello();

        helloServiceReference= context.getServiceReference(HelloService.class.getName());
        HelloService helloService =(HelloService)context.getService(helloServiceReference);
        System.out.println(helloService.sayHello());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Goodbye, World!");
        context.ungetService(helloServiceReference);
    }
}
