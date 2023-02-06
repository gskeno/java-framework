package com.gson.algo.leetcode.monotonicstack;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 股票价格波动
 */
public class StockPrice {
    /**
     * 最大时间戳
     */
    private int maxTimeStamp;

    /**
     * 时间戳对应的价格
     */
    private Map<Integer, Integer> timeToPrice;

    /**
     * 历史的股票价格, 不能使用TreeSet是因为两个不同的时间，可能有同一个价格
     * key是价格，value是该价格出现的次数
     */
    private TreeMap<Integer, Integer> prices;

    public StockPrice() {
        timeToPrice = new HashMap<>();
        prices = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        maxTimeStamp = Math.max(maxTimeStamp, timestamp);
        Integer priceOnTime = timeToPrice.get(timestamp);
        timeToPrice.put(timestamp, price);
        // 该时间戳之前出现过，此次出现需要覆盖
        if (priceOnTime != null){
            // priceOnTime出现次数要-1
            prices.put(priceOnTime, prices.getOrDefault(priceOnTime, 0) -1);
            if (prices.get(priceOnTime) <= 0){
                prices.remove(priceOnTime);
            }
        }
        prices.put(price, prices.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return timeToPrice.get(maxTimeStamp);
    }

    public int maximum() {
        // 最大的key，价格
        return prices.lastKey();
    }

    public int minimum() {
        /**
         * 最小的key，价格
         */
        return prices.firstKey();
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
        stockPrice.update(2, 5); // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
        stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
        stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
        stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
        // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
        stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
        stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
        stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
    }
}
