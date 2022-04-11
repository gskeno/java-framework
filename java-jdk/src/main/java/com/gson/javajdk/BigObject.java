package com.gson.javajdk;

public class BigObject {

    public BigObject(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BigObject{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("BigObject" + this + "被回收了");
    }
}
