package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

public class StartE implements Startable {
    @Override
    public void start() {
        System.out.println("startE");
    }

    @Override
    public void stop() {
        System.out.println("stopE");
    }
}