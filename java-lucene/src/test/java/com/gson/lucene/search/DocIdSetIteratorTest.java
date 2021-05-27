package com.gson.lucene.search;

import org.apache.lucene.search.DocIdSetIterator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DocIdSetIteratorTest {

    @Test
    public void test() throws IOException {
        DocIdSetIterator docIterator = DocIdSetIterator.all(Integer.MAX_VALUE);
        //初始值为-1
        Assert.assertEquals(docIterator.docID(), -1);
        //下一个元素为0
        Assert.assertEquals(docIterator.nextDoc(), 0);
        //当迭代器到达最后一个元素时，再调用会出现预料不到的行为表现
        //跟迭代器的元素数量正相关
        Assert.assertEquals(docIterator.cost(), Integer.MAX_VALUE);

        int min = 100;
        int max = 200;
        DocIdSetIterator rangeIterator = DocIdSetIterator.range(min, max);
        Assert.assertEquals(rangeIterator.docID(), -1);
        //下一个元素，直接跨越到最小值100
        Assert.assertEquals(rangeIterator.nextDoc(), min);
        //跟迭代器的元素数量正相关
        Assert.assertEquals(rangeIterator.cost(), max-min);
    }
}
