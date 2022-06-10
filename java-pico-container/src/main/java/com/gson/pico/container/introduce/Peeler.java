package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

/**
 * 剥皮机
 * @author ruchen
 */
public class Peeler implements Startable {
    private final Peelable peelable;
    public Peeler(Peelable peelable) {
        this.peelable = peelable;
    }
    @Override
    public void start() {
        //peelable.peel();
        System.out.println("Peeler start");
    }
    @Override
    public void stop() {
        System.out.println("Peeler stop");
    }
}
