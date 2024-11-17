package com.gson.javajdk.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D extends C{
    public static void main(String[] args) {
        D d1 = new D();
        d1.setId(2);

        D d2 = new D();
        d2.setId(3);

        D d3 = new D();
        d3.setId(1);

        List<C> listD = new ArrayList<>();
        listD.add(d1);
        listD.add(d2);
        listD.add(d3);

        List<A> listA = new ArrayList<>(3);
        listA.add(new A());
        listA.add(new A());
        listA.add(new A());

        Collections.copy(listA, listD);
        System.out.println(listA);
    }
}
