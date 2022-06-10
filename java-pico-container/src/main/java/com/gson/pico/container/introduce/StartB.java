package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

public class StartB implements Startable {
    @Override
    public void start() {
        System.out.println("startB");
    }

    @Override
    public void stop() {
        System.out.println("stopB");
    }
}
