package com.gson.spring.bean;

public class Tree {

    private int height;

    private String diameter;

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "height=" + height +
                ", diameter='" + diameter + '\'' +
                '}';
    }
}
