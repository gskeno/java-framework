package com.gson.javajdk.jvmgc;

import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    @Test
    public void testWeakHashMap(){
        Map<String,Object> map = new WeakHashMap();
        String s = new String("hello");

        map.put(s, "how are you");
        while (map.containsKey("hello")){
            try {
                Thread.sleep(500);
                //当s没有其他对象引用时，将会回收这个key
                s = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.gc();
        }

    }

    @Test
    public void testWeakHashMap1(){
        Map<String,Object> map = new WeakHashMap();
        String key = new String("hello");
        String value = new String("i am fine");
        map.put(key, value);
        while (map.containsKey("hello")){
            try {
                Thread.sleep(500);
                //value= null，是无法回收entry的
                value = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.gc();
        }

    }
}
