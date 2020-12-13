package com.gson.logback.onjoran;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.util.StatusPrinter;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        Map<ElementSelector, Action> ruleMap = new HashMap<ElementSelector, Action>();

        // Associate "hello-world" pattern with HelloWorldAction
        ruleMap.put(new ElementSelector("hello-world"), new HelloWorldAction());

        // Joran needs to work within a context.
        Context context = new ContextBase();
        SimpleConfigurator simpleConfigurator = new SimpleConfigurator(ruleMap);
        // link the configurator with its context
        simpleConfigurator.setContext(context);

        simpleConfigurator.doConfigure(args[0]);
        //StatusPrinter.print(context);

    }
}
