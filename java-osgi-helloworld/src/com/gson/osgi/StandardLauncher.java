package com.gson.osgi;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * OSGI框架标准启动方式
 */
public class StandardLauncher {

    public static void main(String[] args) throws BundleException, InterruptedException {
        ServiceLoader<FrameworkFactory> frameworkFactoryServiceLoader = ServiceLoader.load(FrameworkFactory.class);
        FrameworkFactory frameworkFactory = frameworkFactoryServiceLoader.iterator().next();
        Map<String,String> properties = new HashMap<>();
        properties.put("osgi.console", "");
        Framework framework = frameworkFactory.newFramework(properties);
        framework.init();

        BundleContext bundleContext = framework.getBundleContext();

        File[] bundles = new File[]{
                new File("/Users/ruchen/osgi_plugins/java-osgi-hello-facade.jar"),
                new File("/Users/ruchen/osgi_plugins/java-osgi-helloworld.jar")
        };
        for(File bundle : bundles){
            bundleContext.installBundle(bundle.toURI().toString());
        }
        framework.start();
//        Bundle[] bundlesSet = bundleContext.getBundles();
//        for(Bundle bundle : bundlesSet){
//            System.out.println(bundle.getBundleId() + " " + bundle.getSymbolicName());
//            bundle.start();
//            System.out.println("----------");
//        }
        framework.waitForStop(0);
    }
}
