package com.gson.pico.container;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

public class GenericUse {
    public static void main(String[] args) {
        MutablePicoContainer pico = new DefaultPicoContainer(new Caching());
        pico.addComponent(Foo.class);
        Foo component1 = pico.getComponent(Foo.class);
        Foo component2 = pico.getComponent(Foo.class);
        System.out.println(component1 == component2);
    }
}
