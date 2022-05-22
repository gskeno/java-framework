package com.gson.design;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流
 * 针对一个访问id，clientId, 1s超过100个访问就限流
 *
 * 注意线程安全与效率
 */
public class RateLimiter {

    private ConcurrentHashMap<String, List<Long>> clientId2Visits = new ConcurrentHashMap();

    public boolean isAllow(String clientIp){
        long current = System.currentTimeMillis();
        if (clientId2Visits.get(clientIp) == null){
            List<Long> visits = new ArrayList<>();
            visits.add(current);
            return true;
        }

        List<Long> visits = clientId2Visits.get(clientIp);
        visits.removeIf(time -> current - time > 1000);

        visits.add(current);
        return visits.size() < 100;
    }
}
