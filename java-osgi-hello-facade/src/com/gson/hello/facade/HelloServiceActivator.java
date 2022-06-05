package com.gson.hello.facade;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class HelloServiceActivator implements BundleActivator {
    ServiceRegistration helloServiceRegistration;
    @Override
    public void start(BundleContext context) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        helloServiceRegistration =context.registerService(HelloService.class.getName(), helloService, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        helloServiceRegistration.unregister();
    }
}
