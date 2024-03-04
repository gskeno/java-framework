package com.gson.javajdk.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class C extends B{

    public static void main(String[] args) {
        C c1 = new C();
        c1.setId(2);

        C c2 = new C();
        c2.setId(3);

        C c3 = new C();
        c3.setId(1);

        List<C> listC = new ArrayList<>();
        listC.add(c1);
        listC.add(c2);
        listC.add(c3);

        List<A> listA = new ArrayList<>(3);
        listA.add(new A());
        listA.add(new A());
        listA.add(new A());

        Collections.copy(listA, listC);
        System.out.println(listA);
    }
}
