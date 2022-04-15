package com.gson.javajdk.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("1","1");
        map.put("3","3");
        map.put("2","2");

        //按插入顺序打印
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>(16,0.75f,true);

        /**
         * {@link LinkedHashMap#head} 最近最少使用
         * {@link LinkedHashMap#tail} 最近使用
         * {@link LinkedHashMap.Entry} 多了before,after两个指针维护访问顺序的双向链表
         *{@link LinkedHashMap#newNode(int, Object, Object, HashMap.Node)}
         */
        map1.put("1","1");
        map1.put("3","3");
        map1.put("2","2");

        //1在头，2在尾
        //按访问顺序打印
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //3被访问，移到尾部
        map1.get("3");
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
