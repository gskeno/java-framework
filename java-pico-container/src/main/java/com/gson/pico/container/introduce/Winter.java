package com.gson.pico.container.introduce;

public class Winter {
    private Snow snow;

    private ColdWind coldWind;

    public void setB(Snow snow) {
        System.out.println("setSnow in Winter invoked");
        this.snow = snow;
    }

    public void setColdWind(ColdWind coldWind){
        System.out.println("setColdWind in Winter invoked");
        this.coldWind = coldWind;
    }

    @Override
    public String toString() {
        return "Winter{" +
                "snow=" + snow +
                ", coldWind=" + coldWind +
                '}';
    }
}
