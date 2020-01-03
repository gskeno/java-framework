package com.gson.spring.bean;

public class Tree {

    private String height;

    private String diameter;

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
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
