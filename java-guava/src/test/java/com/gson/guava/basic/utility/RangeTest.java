package com.gson.guava.basic.utility;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class RangeTest {
    @Test
    public void testRange(){
        //开闭区间的左端点没有区别
        Assert.assertEquals(Range.open(3, 10).lowerEndpoint().intValue(), 3);
        Assert.assertEquals(Range.closed(3, 10).lowerEndpoint().intValue(), 3);

        // 左右闭区间
        ContiguousSet<Integer> set = ContiguousSet.closed(5, 10);
        Assert.assertEquals(set.stream().collect(Collectors.toList()), Lists.newArrayList(5,6,7,8,9,10));

        // 左右开区间
        ContiguousSet<Integer> integers = ContiguousSet.create(Range.open(3, 6), DiscreteDomain.integers());
        List<Integer> collect = integers.stream().collect(Collectors.toList());
        Assert.assertEquals(collect, Lists.newArrayList(4,5));

        // [1,2]与[3,4]取并集
        Range<Integer> span = Range.closed(1, 2).span(Range.closed(3, 4));
        ContiguousSet<Integer> integers1 = ContiguousSet.create(span, DiscreteDomain.integers());
        List<Integer> collect1 = integers1.stream().collect(Collectors.toList());
        Assert.assertEquals(collect1, Lists.newArrayList(1,2,3,4));
    }
}
