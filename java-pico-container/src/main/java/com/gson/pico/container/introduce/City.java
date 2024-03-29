package com.gson.pico.container.introduce;

public class City {

    private House house;

    public void mySynonymForSetHouse(House house){
        System.out.println("mySynonymForSet in City invoked");
        this.house = house;
    }

    public City(){

    }

    public City(House house){
        System.out.println("City Constructor house invoked");
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        System.out.println("setHouse in City invoked," + house);
        this.house = house;
    }

    @Override
    public String toString() {
        return "City{" +
                "house=" + house +
                '}';
    }
}
