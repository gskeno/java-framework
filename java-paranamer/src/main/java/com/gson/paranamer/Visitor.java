package com.gson.paranamer;

import org.gson.poem.Himalaya;

public class Visitor {

    public void visit(Himalaya himalaya){
        System.out.println("Himalaya.__PARANAMER_DATA=" + Himalaya.__PARANAMER_DATA);
        System.out.println("Visitor visit himalaya");
    }
}
