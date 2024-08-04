package com.gson.javajdk.generic;

public class A implements Comparable<A>{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(A o) {
        return this.getId() - o.getId();
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                '}';
    }
}
