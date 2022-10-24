package com.gson.guava.collect;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;

public class ImmutableTest {
    @Test
    public void testImmutable(){
        ImmutableSet<String> immutableSet = ImmutableSet.of("A", "B", "C");
        ImmutableList<String> immutableList = ImmutableList.of("D", "E", "F");
    }


}
