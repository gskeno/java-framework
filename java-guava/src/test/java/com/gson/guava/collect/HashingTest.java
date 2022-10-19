package com.gson.guava.collect;

import com.google.common.base.Strings;
import com.google.common.hash.*;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class HashingTest {
    class Person {
         int id;
         String firstName;
         String lastName;
         int birthYear;
    }

    private Funnel<Person> funnel = new Funnel<Person>() {
        @Override
        public void funnel(Person from, PrimitiveSink into) {
            into.putInt(from.id)
                    // 第一个参数不能为 null
                    .putString(Strings.nullToEmpty(from.firstName), StandardCharsets.UTF_8)
                    .putString(Strings.nullToEmpty(from.lastName), StandardCharsets.UTF_8)
                    .putInt(from.birthYear);
        }
    };
    @Test
    public void testHashing(){
        HashCode hashCode = Hashing.md5().newHasher()
                .putLong(5L)
                .putString("ab", StandardCharsets.UTF_8)
                .putString("cde", StandardCharsets.UTF_8)
                .putObject(new Person(),funnel)
                .hash();
        String hashVal = hashCode.toString();
    }

    @Test
    public void testBloomFilter(){
        int expectedInsertions = 1000;
        double fpp = 0.1d;
        // 预计 布隆过滤器会插入expectedInsertions个元素，且预期在检查待检验元素时，错误识别率控制在fpp以下
        // 即有不高于1%概率 会误识别 元素在集合中，但实际不在集合中
        BloomFilter<Person> bloomFilter = BloomFilter.create(funnel, expectedInsertions, fpp);
        for (int i = 0; i < expectedInsertions; i++) {
            Person person = new Person();
            person.id = i;
            person.firstName = "F" + i;
            bloomFilter.put(person);
        }
        int containsCount = 0;
        int checkTimes = 1000000;
        for (int i = 0; i < checkTimes; i++) {
            Person person = new Person();
            person.id = i;
            person.firstName = "F" + i;
            if (bloomFilter.mightContain(person)){
                containsCount++;
                System.out.println(i  + " may in bloomFilter");
            }
        }
        System.out.println(containsCount);
    }
}
