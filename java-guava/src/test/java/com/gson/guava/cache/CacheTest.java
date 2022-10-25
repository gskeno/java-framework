package com.gson.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
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

}
