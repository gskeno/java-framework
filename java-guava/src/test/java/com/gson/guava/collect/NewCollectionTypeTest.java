package com.gson.guava.collect;

import com.google.common.collect.*;
import org.junit.Assert;
import org.junit.Test;
import sun.jvm.hotspot.runtime.Flags;

import java.util.List;

public class NewCollectionTypeTest {

    @Test
    public void testMultiSet(){
        HashMultiset<Object> multiset = HashMultiset.create();
        multiset.add("A");
        multiset.add("A", 2);
        multiset.add("B", 7);
        // 所有元素出现的总次数 10
        System.out.println(multiset.size());
        // 去重后的元素种类，A, B
        System.out.println(multiset.elementSet());
        // A出现的次数为 3
        System.out.println(multiset.count("A"));
    }

    @Test
    public void testMultiMap(){
        ListMultimap<String, Integer> multimap = MultimapBuilder.hashKeys().arrayListValues().build();
        multimap.put("A", 1);
        multimap.put("A", 2);
        multimap.put("A", 3);
        multimap.put("B", 4);
        multimap.put("C", 5);

        List<Integer> list1 = multimap.get("A");
        // [1,2,3]
        System.out.println(list1);
        List<Integer> list2 = multimap.get("B");
        // [4]
        System.out.println(list2);
    }

    @Test
    public void testBiMap(){
        HashBiMap<String, String> hbm = HashBiMap.<String, String>create();
        hbm.put("taobao","1");
        hbm.put("eleme","2");

        BiMap<String, String> inverse1 = hbm.inverse();
        BiMap<String, String> inverse2 = hbm.inverse();

        Assert.assertEquals(inverse1, inverse2);
        Assert.assertEquals(inverse1.get("1"), "taobao");
    }

    /**
     * 表格
     */
    @Test
    public void testTable(){
        HashBasedTable<Integer, Integer, Double> weightedGraph = HashBasedTable.create();
        int v1 = 1, v2 = 2, v3 = 3;
        weightedGraph.put(v1, v2, 4d);
        weightedGraph.put(v1, v3, 20d);
        weightedGraph.put(v2, v3, 5d);

        System.out.println(weightedGraph.row(v1)); // returns a Map mapping v2 to 4, v3 to 20
        System.out.println(weightedGraph.column(v3)); // returns a Map mapping v1 to 20, v2 to 5
    }

    @Test
    public void testClassToInstanceMap(){
        MutableClassToInstanceMap<Number> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.put(Integer.class, Integer.valueOf(0));
        classToInstanceMap.put(Float.class, Float.valueOf(0f));

        Assert.assertEquals(classToInstanceMap.get(Integer.class), Integer.valueOf(0));
        Assert.assertEquals(classToInstanceMap.get(Float.class), Float.valueOf(0f));
    }
}
