package com.gson.spring.bean;

public class House {
    private Chair chair;

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public Chair getChair() {
        return chair;
    }

    public void init(){
        System.out.println("House init");
    }
}
