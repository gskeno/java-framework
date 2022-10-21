package com.gson.guava;

public class User implements Comparable<User> {

    static {
        System.out.println("i am user");
    }

    public User() {
    }

    public User(String site, String name) {
        this.site = site;
        this.name = name;
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

    @Override
    public int compareTo(User o) {
        return name.compareTo(o.name);
    }
}
