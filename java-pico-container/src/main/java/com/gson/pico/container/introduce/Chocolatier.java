package com.gson.pico.container.introduce;


import org.picocontainer.injectors.Provider;

public class Chocolatier implements Provider {
    public Chocolate provide(CocaoBeans cocaoBeans) {
        return new Chocolate(cocaoBeans);
    }
}
