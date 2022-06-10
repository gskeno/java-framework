package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

public class StartA implements Startable {
    @Override
    public void start() {
        System.out.println("startA");
    }

    @Override
    public void stop() {
        System.out.println("stopA");
    }
}
