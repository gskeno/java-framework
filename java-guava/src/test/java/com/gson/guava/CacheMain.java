package com.gson.guava;

import com.google.common.cache.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CacheMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
        cache.get( UUID.randomUUID().toString());
        cache.get( UUID.randomUUID().toString());
        cache.get( UUID.randomUUID().toString());
    }
}
