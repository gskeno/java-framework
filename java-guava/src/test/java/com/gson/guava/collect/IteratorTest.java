package com.gson.guava.collect;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

public class IteratorTest {
    // 获取一个迭代器, 可以跳过空元素
    public static Iterator<String> skipNulls(final Iterator<String> in) {
        return new AbstractIterator<String>() {
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
    }

    @Test
    public void testNullEle(){
        // size = 3
        Set<String> sets = Sets.newHashSet("A",null, "B");
        Iterator<String> iterator = sets.iterator();
        Iterator<String> skipNullIterator = skipNulls(iterator);
        // A, B
        while (skipNullIterator.hasNext()){
            System.out.println(skipNullIterator.next());
        }

        Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) { // note the initial value!
            protected Integer computeNext(Integer previous) {
                return (previous == 1 << 30) ? null : previous * 2;
            }
        };
        // 1
        System.out.println(powersOfTwo.next());
        // 2
        System.out.println(powersOfTwo.next());
        // 4
        System.out.println(powersOfTwo.next());
        // 8
        System.out.println(powersOfTwo.next());
        // 16
        System.out.println(powersOfTwo.next());
    }

}
