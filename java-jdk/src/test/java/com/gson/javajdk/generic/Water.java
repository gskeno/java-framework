package com.gson.javajdk.generic;

public class Water implements Comparable<Water>{
    private int saltPercentage;

    public int getSaltPercentage() {
        return saltPercentage;
    }

    public void setSaltPercentage(int saltPercentage) {
        this.saltPercentage = saltPercentage;
    }

    @Override
    public int compareTo(Water o) {
        return this.getSaltPercentage() - o.getSaltPercentage();
    }

    @Override
    public String toString() {
        return "Water{" +
                "saltPercentage=" + saltPercentage +
                '}';
    }
}
