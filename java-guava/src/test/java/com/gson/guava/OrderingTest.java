package com.gson.guava;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.sun.istack.internal.Nullable;
import org.junit.Test;

public class OrderingTest {

    @Test
    public void test1(){
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });

        Foo foo1 =  null;
        Foo foo2 = new Foo();
        Foo foo3 = new Foo();
        foo3.sortedBy = "111";

        Foo foo4 = new Foo();
        foo4.sortedBy = "21";

        int compare1 = ordering.compare(foo1, foo2);
        System.out.println(compare1);

    }



    class Foo {
        @Nullable
        String sortedBy;
        int notSortedBy;
    }
}
