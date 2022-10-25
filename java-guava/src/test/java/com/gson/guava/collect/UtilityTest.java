package com.gson.guava.collect;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class UtilityTest {
    @Test
    public void testIterables(){
        Iterable<Integer> concat = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6));
        // return [1,2,3,4,5,6]
        System.out.println(concat);
        // return 6
        System.out.println(Iterables.getLast(concat));
        // return "A"
        System.out.println(Iterables.getOnlyElement(Sets.newHashSet("A")));
    }

    @Test
    public void testMultiSets(){
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);
        // false, multiset1中a出现的次数少
        System.out.println(Multisets.containsOccurrences(multiset1, multiset2));
        // multiset2 now contains 3 occurrences of "a"
        Multisets.removeOccurrences(multiset2, multiset1);
    }

    @Test
    public void testMultiMap(){
        ImmutableSet<String> digits = ImmutableSet.of(
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };
        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits, lengthFunction);
        System.out.println(digitsByLength);
        /*
         * digitsByLength maps:
         *  3 => {"one", "two", "six"}
         *  4 => {"zero", "four", "five", "nine"}
         *  5 => {"three", "seven", "eight"}
         */
        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        // SetMultimap 不会存储重复的key-value
        SetMultimap<String, Integer> setMultimap = Multimaps.forMap(map);
        HashMultimap<Integer, String> hashMultimap = Multimaps.invertFrom(setMultimap, HashMultimap.create());
        System.out.println(hashMultimap);
    }

    @Test
    public void testSetMultiMap(){
        SetMultimap<String, Integer> setMultimap = MultimapBuilder.SetMultimapBuilder.hashKeys().hashSetValues().build();
        setMultimap.put("A", 1);
        setMultimap.put("A", 1);
        setMultimap.put("B", 1);
        // {A=[1], B=[1]}
        System.out.println(setMultimap);

        ListMultimap<String, Integer> listMultimap = MultimapBuilder.SetMultimapBuilder.hashKeys().arrayListValues().build();
        listMultimap.put("A", 1);
        listMultimap.put("A", 1);
        listMultimap.put("B", 1);
        // {A=[1, 1], B=[1]}
        System.out.println(listMultimap);
    }
}
