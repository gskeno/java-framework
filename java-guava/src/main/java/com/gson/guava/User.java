package com.gson.guava;

public class User {

    static {
        System.out.println("i am user");
    }
    private String site;

    private String name;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "site='" + site + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
