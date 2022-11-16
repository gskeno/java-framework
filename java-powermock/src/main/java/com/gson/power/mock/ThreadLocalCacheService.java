package com.gson.power.mock;

public class ThreadLocalCacheService implements CacheService{

    private DBService dbService;

    @Override
    public <V, K> V getValue(K k) {
        Result result = dbService.getResult((String) k);
        return (V)result;
    }
}
