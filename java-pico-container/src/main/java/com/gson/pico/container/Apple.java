package com.gson.pico.container;

public class Apple {
    private Orange orange;
    private Pear pear;
    private Banana banana;
    public void setOrange(Orange orange) {
        this.orange = orange;
    }
    public void setPear(Pear pear) {
        this.pear = pear;
    }
    public void setBanana(Banana banana) {
        this.banana = banana;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "orange=" + orange +
                ", pear=" + pear +
                ", banana=" + banana +
                '}';
    }
}
