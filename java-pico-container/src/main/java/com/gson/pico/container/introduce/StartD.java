package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

public class StartD implements Startable {
    @Override
    public void start() {
        System.out.println("startD");
    }

    @Override
    public void stop() {
        System.out.println("stopD");
    }
}