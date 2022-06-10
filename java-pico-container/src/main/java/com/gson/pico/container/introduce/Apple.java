package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

/**
 * http://picocontainer.com/introduction.html
 */
public class Apple implements Peelable, Startable {
    public Apple(){
        System.out.println("Apple constructor performed");
    }
    @Override
    public void peel() {

    }

    @Override
    public void start() {
        System.out.println("Peelable apple start");
    }

    @Override
    public void stop() {
        System.out.println("Peelable apple stop");
    }
}
