package com.gson.pico.container.introduce;

/**
 * 榨汁器
 */
public class Juicer {
    private final Peelable peelable;
    private final Peeler peeler;
    public Juicer(Peelable peelable, Peeler peeler) {
        this.peelable = peelable;
        this.peeler = peeler;
    }
}
