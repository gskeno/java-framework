package com.gson.javajdk.util;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * 二叉搜索树
 */
public class TreeMapTest {

    @Test
    public void test(){
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();

        treeMap.put(1,10);
        treeMap.put(2,20);
        treeMap.put(3,30);
        // key小于等于2的最大的节点
        Map.Entry<Integer, Integer> entry = treeMap.floorEntry(2);
        System.out.println(entry);
    }
}
