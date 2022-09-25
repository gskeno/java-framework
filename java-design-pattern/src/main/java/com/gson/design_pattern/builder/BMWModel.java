package com.gsonkeno.pattern.builder;

/**
 * Created by gaosong on 2018-01-19
 */
public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("宝马车启动是这样的");
    }

    @Override
    protected void stop() {
        System.out.println("宝马车停车是这样的");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马车是这样鸣喇叭的");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马车的引擎声是这样的");
    }
}
