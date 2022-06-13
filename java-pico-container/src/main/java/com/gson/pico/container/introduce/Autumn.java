package com.gson.pico.container.introduce;

public class Autumn {

    private Leaf leaf;
    private River river;

    public Autumn() {
    }

    public Autumn (Leaf leaf){
        System.out.println("Leaf Constructor in Autumn invoked ");
        this.leaf = leaf;
    }

    @Override
    public String toString() {
        return "Autumn{" +
                "leaf=" + leaf +
                ", river=" + river +
                '}';
    }
}
