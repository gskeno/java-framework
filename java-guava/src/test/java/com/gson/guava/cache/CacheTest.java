package com.gson.guava.cache;

import com.google.common.cache.*;
import com.google.common.reflect.Reflection;
import com.gson.guava.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.zip.DeflaterOutputStream;

public class CacheTest {

    @Test
    public void testGet() throws ExecutionException {
        LoadingCache<String, User> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, User>() {
                            public User load(String key) {
                                System.out.println("build cache:" + key);
                                return new User(null, key);
                            }
                        });
        loadingCache.get("A");
        loadingCache.get("A");
    }

    @Test
    public void testGetIfPresent(){
        LoadingCache<String, User> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, User>() {
                            public User load(String key) {
                                System.out.println("build cache:" + key);
                                return new User(null, key);
                            }
                        });
        loadingCache.getIfPresent("A");
        loadingCache.getIfPresent("A");
        loadingCache.getIfPresent("A");
    }
    /**
     * 加载缓存策略之一 CacheLoader
     */
    @Test
    public void testCacheLoader() {
        // 使类强制初始化,静态代码块会被执行
        Reflection.initialize(User.class);

        LoadingCache<String, User> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, User>() {
                            public User load(String key) {
                                System.out.println("build cache:" + key);
                                return new User(null, key);
                            }
                        });
        // print "build cache:A"
        loadingCache.getUnchecked("A");
        // not print "build cache:A"
        loadingCache.getUnchecked("A");
    }

    @Test
    public void testGetCallable() throws ExecutionException {
        Reflection.initialize(User.class);

        // LocalCache.LocalManualCache
        Cache<String, User> cache = CacheBuilder.newBuilder().build();
        String key = "A";
        User user = cache.get(key, new Callable<User>() {
            @Override
            public User call() throws Exception {
                System.out.println("build cache:" + key);
                return new User(null, key);
            }
        });
        System.out.println(user);
        cache.getIfPresent(key);
    }

    LoadingCache<String, String> loadingCacheBySize = CacheBuilder.newBuilder().maximumSize(3).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            System.out.println("load " + key);
            return key;
        }
    });

    @Test
    public void testEvictBySize() throws ExecutionException {
        loadingCacheBySize.get("A");
        loadingCacheBySize.get("B");
        loadingCacheBySize.get("C");
        // evict A
        loadingCacheBySize.get("D");
        // C->D->B
        loadingCacheBySize.get("C");
        // B->C->D
        loadingCacheBySize.get("B");
        // print "load A", because A not in cache, evict D, after, A->B->C
        loadingCacheBySize.get("A");
    }


    LoadingCache<String, String> loadingCacheByWeight = CacheBuilder.newBuilder()
            .maximumWeight(100).weigher(new Weigher<String, String>() {
                @Override
                public int weigh(String key, String value) {
                    System.out.println(key + " weight is " + key.length() * 10);
                    return key.length() * 10;
                }
            }).build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    System.out.println("load " + key);
                    return key;
                }
            });

    /**
     * 之前理解有偏差，以为 首先逐出比重 相近的缓存对象，逐出这一个对象后，
     * 新对象放进cache。
     * 其实不然，这里只是提供了一个根据weight来计算元素何时达到 容量阈值 size而已
     *
     * @throws ExecutionException
     */
    @Test
    public void testEvictByWeight() throws ExecutionException {
        loadingCacheByWeight.get("ABCD");
        loadingCacheByWeight.get("ABC");
        loadingCacheByWeight.get("AB");
        loadingCacheByWeight.get("A");
        // put B，will evict "ABCD"
        loadingCacheByWeight.get("B");
        loadingCacheByWeight.get("ABCD");
    }

    @Test
    public void testTimedEviction() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("load " + key);
                        return key;
                    }
                });
        // print "load A"
        cache.get("A");
        cache.get("A");
        Thread.sleep(2500);
        // no print
        cache.get("A");
        Thread.sleep(1000);
        // print "load A"
        cache.get("A");

        LoadingCache<String, String> cache1 = CacheBuilder.newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("load " + key);
                        return key;
                    }
                });
        // print load B
        cache1.get("B");
        cache1.get("B");
        Thread.sleep(3500);
        // print load B
        cache1.get("B");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            cache1.get("B");
        }
    }

    /**
     * 基于 弱引用key 逐出
     */
    @Test
    public void testWeakReferenceBaseEviction() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().weakKeys().removalListener(
                new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        // 如果value是大对象，要主动将其失效掉
                        System.out.println(notification.getKey() + ":" + notification.getValue()
                                + " is " + notification.wasEvicted());
                    }
                }).build();

        cache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        System.out.println(cache.asMap());
        System.gc();
        // 得到 {}
        System.out.println(cache.asMap());
    }


    @Test
    public void testWeakKeys() throws ExecutionException, InterruptedException {
        LoadingCache<User, String> cache = CacheBuilder.newBuilder()
                .weakKeys()
                .removalListener(new RemovalListener<User, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<User, String> notification) {
                        User key = notification.getKey();
                        boolean evicted = notification.wasEvicted();
                        System.out.println(key + " is evicted: " + evicted);
                        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
                        for(int i=0;i<stack.length;i++){
                            System.out.println(stack[i].getClassName()+ "#"+stack[i].getMethodName() );
                        }
                    }
                })
                .build(new CacheLoader<User, String>() {
                    @Override
                    public String load(User key) throws Exception {
                        return  "value";
                    }
                });
        cache.get(new User(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        System.out.println("size=" + cache.size() + ", map=" + cache.asMap());
        System.gc();
        Thread.sleep(1000);

        // 执行size方法时，还没有清除无效的 键值对。在遍历时，对于只有弱引用的key，该entry会被移除掉
        System.out.println("size=" +cache.size() + ", map=" + cache.asMap());
        System.out.println("size=" +cache.size() + ", map=" + cache.asMap());

        cache.get(new User(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        Thread.sleep(2000);
    }

    @Test
    public void testWeakKeys1() throws ExecutionException, InterruptedException {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .weakKeys()
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        String key = notification.getKey();
                        boolean evicted = notification.wasEvicted();
                        System.out.println("key:" + key + ", value=" + notification.getValue() +  " is evicted: " + evicted);
                        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
                        for(int i=0;i<stack.length;i++){
                            System.out.println(stack[i].getClassName()+ "#"+stack[i].getMethodName() );
                        }
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return  key + "_value";
                    }
                });
        cache.get(UUID.randomUUID().toString());
        System.out.println("size=" + cache.size() + ", map=" + cache.asMap());
        System.gc();
        Thread.sleep(1000);

        // 执行size方法时，还没有清除无效的 键值对。在遍历时，对于只有弱引用的key，该entry会被移除掉
        System.out.println("size=" +cache.size() + ", map=" + cache.asMap());
        System.out.println("size=" +cache.size() + ", map=" + cache.asMap());

        Thread.sleep(2000);
        cache.get( UUID.randomUUID().toString());

    }

    @Test
    public void testWeakKeys2() throws ExecutionException, InterruptedException {

        LoadingCache<Double, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1)
                .weakKeys().removalListener(new RemovalListener<Double, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Double, String> notification) {
                        System.out.println("key " + notification.getKey()
                                + ", value " + notification.getValue()
                                + " evicted");
                    }
                }).build(new CacheLoader<Double, String>() {
                    @Override
                    public String load(Double key) throws Exception {
                        return key.toString();
                    }
                });
        cache.get(Math.random());
        System.out.println("cache size :" + cache.size());
        System.out.println("cache: " + cache.asMap());
        System.gc();
        System.out.println("cache size :" + cache.size());
        System.out.println("cache: " + cache.asMap());
        cache.get(Math.random());

    }

    @Test
    public void testWeakValues() throws ExecutionException, InterruptedException {
        Reflection.initialize(User.class);

        LoadingCache<User, String> cache = CacheBuilder.newBuilder()
                .weakValues()
                .removalListener(new RemovalListener<User, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<User, String> notification) {
                        User key = notification.getKey();
                        boolean evicted = notification.wasEvicted();
                        System.out.println(key + " is evicted: " + evicted);
                    }
                })
                .build(new CacheLoader<User, String>() {
                    @Override
                    public String load(User key) throws Exception {
                        System.out.println("user: " + key);
                        return  "value";
                    }
                });
        User user = new User(null, "gs");
        cache.get(user);
        System.gc();
        Thread.sleep(1000);
        System.out.println("cache size " + cache.size());
        cache.get(user);
    }

    @Test
    public void testRemoveListener1(){
        Cache cache = CacheBuilder.newBuilder()
                .weakKeys().removalListener(new RemovalListener<Double, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Double, String> notification) {
                        System.out.println(notification.getKey() + " evicted");
                    }
                }).build();
        cache.put(Math.random(), "a");
        System.out.println("cache: " + cache.asMap());
        System.gc();
        System.out.println("cache: " + cache.asMap());
    }
    @Test
    public void testRemoveListener2() throws ExecutionException {
        LoadingCache<Double, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2).removalListener(new RemovalListener<Double, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Double, String> notification) {
                        System.out.println(notification.getKey() + " evicted");
                    }
                }).build(new CacheLoader<Double, String>() {
                    @Override
                    public String load(Double key) throws Exception {
                        return key.toString();
                    }
                });
        cache.get(Math.random());
        cache.get(Math.random());
        System.out.println("cache: " + cache.asMap());

        cache.get(Math.random());
        System.gc();
        System.out.println("cache: " + cache.asMap());
    }

    @Test
    public void testStatistics() throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).recordStats().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key;
            }
        });
        cache.get("A");
        CacheStats stats = cache.stats();
        // 不命中共1次
        Assert.assertEquals(stats.missCount(), 1);
        // 加载1次
        Assert.assertEquals(stats.loadSuccessCount(), 1);

        cache.get("A");
        // 命中1次
        Assert.assertEquals(cache.stats().hitCount(), 1);
        cache.get("A");
        // 命中2次
        Assert.assertEquals(cache.stats().hitCount(), 2);

        cache.get("B");
        cache.get("C");
        // 不命中共3次
        Assert.assertEquals(cache.stats().missCount(), 3);
        // 加载共3次
        Assert.assertEquals(cache.stats().loadSuccessCount(), 3);

        // 共逐出1次
        cache.get("D");
        Assert.assertEquals(cache.stats().evictionCount(), 1);
        cache.get("E");
        // 共逐出2次
        Assert.assertEquals(cache.stats().evictionCount(), 2);
    }
}
