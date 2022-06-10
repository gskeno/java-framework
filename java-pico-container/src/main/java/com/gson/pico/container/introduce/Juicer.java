package com.gson.pico.container.introduce;

import org.picocontainer.Startable;

/**
 * 榨汁器
 */
public class Juicer implements Startable {
    @Override
    public void start() {
        System.out.println("Juicer start");
    }

    @Override
    public void stop() {
        System.out.println("Juicer stop");
    }

    private  Peelable peelable;
    private  Peeler peeler;
    public Juicer(Peelable peelable, Peeler peeler) {
        this.peelable = peelable;
        this.peeler = peeler;
    }

//    public Juicer() {
//    }

    @Override
    public String toString() {
        return "Juicer{" +
                "peelable=" + peelable +
                ", peeler=" + peeler +
                '}';
    }


}
