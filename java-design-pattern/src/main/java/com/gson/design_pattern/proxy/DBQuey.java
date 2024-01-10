package com.gson.design_pattern.proxy;

/**
 * Created by gaosong on 2017-11-13
 */
public class DBQuey implements IDBQuery {
    public DBQuey() {
        try {
            //初始化DBQuery实例耗时较久
            Thread.sleep(2*1000);
            System.out.println("睡眠2s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String request() {
        return "request";
    }
}
