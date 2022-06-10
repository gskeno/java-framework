package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

public class StartC implements Startable {
    @Override
    public void start() {
        System.out.println("startC");
    }

    @Override
    public void stop() {
        System.out.println("stopC");
    }
}