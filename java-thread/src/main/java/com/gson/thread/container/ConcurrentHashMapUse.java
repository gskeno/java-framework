package com.gsonkeno.jdk.training.concurrent.container;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gaosong on 2017-09-11
 */
public class ConcurrentHashMapUse {

    public static ConcurrentHashMap<String,Object> chm = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        chm.put("A",2);

        System.out.println(chm.putIfAbsent("B",3));

        System.out.println(chm.putIfAbsent("A","3"));//建对应的值不存在方设值成功

        System.out.println(chm.get("A"));

    }


}
