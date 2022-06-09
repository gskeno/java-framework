package com.gson.javajdk.clazzloader;

public class Foo {
    public void invokeDeclaredField(Class<Bar> barClass) throws NoSuchFieldException {
        barClass.getDeclaredField("name");
    }

    public void method(Bar bar) throws NoSuchFieldException {
        bar.getClass().getDeclaredField("name");
    }
}
