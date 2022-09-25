package com.gsonkeno.pattern.builder;

/**
 * Created by gaosong on 2018-01-19
 */
public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("奔驰车启动是这样的");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰车停止是这样的");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰车是这样鸣喇叭的");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎声音是这样的");
    }
}
