package com.gson.pico.container;

public class Orange {
    private String desc;

    public Orange(){
        this.desc = "橘子是酸的";
    }

    public String getDesc() {
        return desc;
    }

//    public void setDesc(String desc) {
//        this.desc = desc;
//    }

    @Override
    public String toString() {
        return "Orange{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
