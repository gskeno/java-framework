package com.gson.lucene.search;

import org.apache.lucene.search.DocIdSetIterator;
import org.junit.Test;

import java.io.IOException;

public class DocIdSetIteratorTest {

    @Test
    public void test() throws IOException {
        DocIdSetIterator docIterator = DocIdSetIterator.all(Integer.MAX_VALUE);
        //当迭代器到达最后一个元素时，再调用会出现预料不到的行为表现
        System.out.println(docIterator.nextDoc());
    }
}
