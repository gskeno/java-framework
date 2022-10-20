package com.gson.jackson.model;

public class Address {

    private String cityCode;

    private String city;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public Address(String cityCode, String city) {
        this.cityCode = cityCode;
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cityCode='" + cityCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
