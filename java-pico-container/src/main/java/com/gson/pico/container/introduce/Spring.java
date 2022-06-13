package com.gson.pico.container.introduce;

import org.picocontainer.annotations.Inject;

public class Spring {
    @Inject private Flower flower;

    public Spring(){
        System.out.println("Spring Constructor invoked");
    }

    @Override
    public String toString() {
        return "Spring{" +
                "flower=" + flower +
                '}';
    }
}
