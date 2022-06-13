package com.gson.pico.container.introduce;

import org.picocontainer.annotations.Inject;

public class Summer {

    private RainStorm rainStorm;

    @Inject
    public void injectRainStorm(RainStorm rainStorm){
        System.out.println("injectRainStorm in Summer invoked");
        this.rainStorm = rainStorm;
    }

    public Summer(){
        System.out.println("Summer noArg Constructor invoked");

    }

    @Override
    public String toString() {
        return "Summer{" +
                "rainStorm=" + rainStorm +
                '}';
    }
}
