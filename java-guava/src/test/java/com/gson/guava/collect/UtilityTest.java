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

    }
}
