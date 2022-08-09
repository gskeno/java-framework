package com.gson.guava;

import com.google.common.cache.*;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.UncheckedExecutionException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * https://segmentfault.com/a/1190000011105644
 */
public class CacheBuilderTest extends Assert {

    @Test
    public void testExpireAfterWrite() throws ExecutionException, InterruptedException {
        LoadingCache<String,Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        System.out.println("load" + key);
                        if (key.equals("s1")){
                            return 1;
                        }
                        return 2;
                    }
                });
        // 没有，将会加载
        cache.get("s1");
        // 5秒内不会再加载
        cache.get("s1");
        Thread.sleep(6*1000);
        cache.get("s1");
    }

    /**
     * 一个基本的CacheBuilder的测试,
     * {@link Cache#getIfPresent(Object)}
     */
    @Test
    public void testPureBuilder() {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("name", "gs");
        assertEquals(cache.getIfPresent("name"), "gs");
        assertNull(cache.getIfPresent("sex"));
    }


    @Test
    public void testCacheLoaderBuiler() throws ExecutionException {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, String>() {
            @Override
            public String load(Integer key) throws FileNotFoundException {
                if (key < 5) {
                    return "key less 5";
                } else if (key < 10) {
                    throw new RuntimeException("key in [5,10)");
                } else {
                    throw new FileNotFoundException("key in [10,)");
                }
            }
        });

        assertEquals(cache.getUnchecked(1), "key less 5");
        assertEquals(cache.get(1), "key less 5");

        try {
            cache.get(5);
            fail();
        } catch (UncheckedExecutionException e) {
        }

        //这是不友好的，CacheLoader.load方法抛出受检异常时,不适合调用cache.getUnchecked方法
        try {
            cache.getUnchecked(10);
            fail();
        } catch (UncheckedExecutionException e) {
        }
    }

    /**
     * 固定容量回收，回收最近最少使用的
     */
    @Test
    public void testSizeBasedEviction() {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(3).build();
        cache.put("A", "A");
        cache.put("B", "B");
        cache.put("C", "C");
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet("A", "B", "C")));

        cache.put("D", "D");
        System.out.println(cache.asMap());
        //由于A最近最少使用，逐出
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet("B", "C", "D")));

        cache.getIfPresent("B");
        cache.put("E", "E");
        //由于B被读取，所以C最少使用，C被逐出
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet("B", "D", "E")));
        System.out.println(cache.getIfPresent("C"));
        System.out.println(cache.getIfPresent("D"));
    }

    /**
     * 基于固定容量，不同的key容量占比是不同的，依然是回收最近不常使用的；
     * 但所有key的容量和不能超过最大容量限制
     */
    @Test
    public void testWeightEviction() {
        Cache<Integer, Object> cache = CacheBuilder.newBuilder().maximumWeight(10).weigher(new Weigher<Integer, Object>() {
            @Override
            public int weigh(Integer key, Object value) {
                return key;
            }
        }).build();
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet(1, 2, 3)));
        cache.put(5, 5);
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet(2, 3, 5)));

        cache.getIfPresent(2);
        cache.put(6, 6);
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet(2, 6)));

        cache.put(11, 11);
        assertTrue(cache.asMap().keySet().containsAll(Sets.newHashSet()));
    }

    /**
     * 一定时间内没有被访问，就过期逐出
     */
    @Test
    public void testExpireAfterAccessEviction() throws InterruptedException {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build();
        cache.put("A", "A");
        assertEquals(cache.getIfPresent("A"), "A");
        Thread.sleep(2 * 1000);
        assertEquals(cache.getIfPresent("A"), "A");
        //因为被读过，需要再睡3s以上才过期
        Thread.sleep(3200);
        assertNull(cache.getIfPresent("A"));

    }

    /**
     * key 被写入一定时间后过期
     *
     * @throws InterruptedException
     */
    @Test
    public void testExpireAfterWriteEviction() throws InterruptedException {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();
        cache.put("A", "A");
        assertEquals(cache.getIfPresent("A"), "A");
        System.out.println("逐出前size=" + cache.size());
        Thread.sleep(2 * 1000);
        //睡2s未过期
        assertEquals(cache.getIfPresent("A"), "A");
        Thread.sleep(2 * 1000);
        System.out.println("睡2s后size=" + cache.size());


        //睡4秒已过期
        assertNull(cache.getIfPresent("A"));
        System.out.println("睡4s后size=" + cache.size());
    }

    /**
     * 弱引用清除策略
     */
    @Test
    public void testWeakReferenceEviction() throws InterruptedException {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().weakValues().build();
        Object value = new Object();
        cache.put("A", value);
        cache.put("B", "B1");
        System.out.println(cache.asMap().toString());
        System.out.println(cache.size());
        assertTrue(cache.asMap().keySet().contains("A"));

        //原对象不在有强引用
        value = new Object();
        //如果不加gc，原对象不会被清除
        Thread.sleep(3 * 1000);
        System.gc();
        assertEquals(cache.size(), 2);
        System.out.println("gc后 " + cache.asMap().toString());
        System.out.println("gc后size= " + cache.asMap().size());

        assertNull(cache.getIfPresent("A"));
        System.out.println("gc后getA后size= " + cache.asMap().size());

    }

    @Test
    public void testRemovalListerner() {
        RemoveListener removeListener = new RemoveListener();
        Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(3).removalListener(removeListener).build();
        cache.put("A", "A");
        cache.put("B", "B");
        cache.put("C", "C");
        assertNull(removeListener.getReason());
        cache.put("D", "D");
        assertNotNull(removeListener.getReason());


    }

    class RemoveListener implements RemovalListener {
        private String reason;

        @Override
        public void onRemoval(RemovalNotification notification) {
            this.reason = String.format("removed key=%s, value=%s, cause=%s",
                    notification.getKey(), notification.getKey(), notification.getCause());
            System.out.println(reason);
        }

        public String getReason() {
            return reason;
        }
    }

    @Test
    public void testStat() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .recordStats() //开启统计信息开关
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");

        assertEquals(cache.stats().toString(),
                "CacheStats{hitCount=3, missCount=3, loadSuccessCount=0, " +
                        "loadExceptionCount=0, totalLoadTime=0, evictionCount=1}");

    }


}
