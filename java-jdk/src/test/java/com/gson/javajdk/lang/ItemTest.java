package com.gson.javajdk.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemTest {

    @Test
    public void testItem(){
        Item item1 = new Item(1L,"name","desc");
        Item item2 = new Item(1L,"name","desc");

        List<Item> items1 = new ArrayList<>();
        items1.add(item1);

        List<Item> items2 = new ArrayList<>();
        items2.add(item2);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        System.out.println(items.contains(item2));
        items.add(item2);

        System.out.println(items);
    }
}
