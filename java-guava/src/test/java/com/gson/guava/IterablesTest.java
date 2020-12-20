package com.gson.guava;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import org.junit.Test;

public class IterablesTest {
    @Test
    public void test() {
        Iterable<Integer> concatenated = Iterables.concat(

                Ints.asList(1, 2, 3),

                Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6

        Integer lastAdded = Iterables.getLast(concatenated);
        System.out.println(lastAdded);

    }
}
