package com.gson.algo.tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * 排日历，book(int start, int end) 在日历表增加一个事项安排，时间是[start, end) 左闭右开，
 * 请返回是否可以成功添加该事项。
 * <p>
 * 找出已添加事项中开始时间 < start的最晚的一个，和已添加事件中开始时间 >= end 最早的那一个，
 * 如果这两个事项与待添加的事项都没有重叠，则当前事项能够成功添加进去。
 */
public class MyCalendar {
    private TreeMap<Integer, Integer> start2EndMap = new TreeMap<>();

    public boolean book(int start, int end) {
        // 小于等于start的最大值, 根据key排序
        Map.Entry<Integer, Integer> event = start2EndMap.floorEntry(start);
        // 开始时间不大于start的事项中开始时间最晚的一个事项，结束时间也大于start，则存在重叠
        if (event != null && event.getValue() > start) {
            return false;
        }

        // 开始时间不小于start的事项中开始时间最早的那个事项，开始时间小于当前事项的结束时间，则存在重叠
        event = start2EndMap.ceilingEntry(start);
        if (event != null && end > event.getKey()) {
            return false;
        }

        start2EndMap.put(start, end);
        return true;
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        boolean result = myCalendar.book(15, 25);
        System.out.println(result);

        result = myCalendar.book(20, 25);
        System.out.println(result);

        result = myCalendar.book(25, 30);
        System.out.println(result);
    }
}
