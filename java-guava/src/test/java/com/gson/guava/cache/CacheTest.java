package com.gson.guava.cache;

import com.google.common.cache.*;
import com.google.common.reflect.Reflection;
import com.gson.guava.User;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CacheTest {


    /**
     * 加载缓存策略之一 CacheLoader
     */
    @Test
    public void testCacheLoader(){
        Reflection.initialize(User.class);

        LoadingCache<String , User> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, User>() {
                            public User load(String key){
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
        // D->C->B
        loadingCacheBySize.get("D");
        // C->D->B
        loadingCacheBySize.get("C");
        // B->C->D
        loadingCacheBySize.get("B");
        // print "load A", because A not in cache, after, A->B->C
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

}
