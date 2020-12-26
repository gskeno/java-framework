package com.gson.algo.linklist;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {

    @Test
    public void test() {
        LinkedList<String> link = new LinkedList<>();
        link.add("3");
        link.add("5");
        link.add("4");
        link.add("7");
        link.add("6");

        Iterator<String> iterator = link.iterator();
        String pre = null;
        String cur = null;
        if (iterator.hasNext()) {
            cur = iterator.next();
        }
        while (iterator.hasNext()) {
            String element = iterator.next();
            pre = cur;
            cur = element;
            System.out.println("pre=" + pre + ",cur=" + cur);
        }
        System.out.println(link);
    }

    @Test
    public void testRemove() {
        LinkedList<String> link = new LinkedList<>();
        link.add("3");
        link.add("5");
        link.add("4");
        link.add("7");
        link.add("6");
        Iterator<String> iterator = link.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();

            if (element.equals("4")){
                iterator.remove();
            }
        }

        System.out.println(link);
    }
}
