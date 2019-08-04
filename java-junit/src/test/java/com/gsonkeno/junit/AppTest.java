package com.gsonkeno.junit;

import java.util.ArrayList;
import java.util.List;

public abstract class AppTest {

    static {
        System.out.println("AppTest static block");
    }

    public static List<Object[]> getData(){
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"1","2"});
        data.add(new Object[]{"3","4"});

        return data;
    }

}
