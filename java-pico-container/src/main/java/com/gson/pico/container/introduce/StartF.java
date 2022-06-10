package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

public class StartF implements Startable {
    @Override
    public void start() {
        System.out.println("startF");
    }

    @Override
    public void stop() {
        System.out.println("stopF");
    }
}