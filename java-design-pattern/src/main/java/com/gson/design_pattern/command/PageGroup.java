package com.gsonkeno.pattern.command;

/**
 * Created by gaosong on 2018-01-16
 */
public class PageGroup extends Group {
    @Override
    void find() {
        System.out.println("找到页面组");
    }

    @Override
    void add() {
        System.out.println("增加一处页面");
    }

    @Override
    void delete() {
        System.out.println("删除一处页面");
    }

    @Override
    void change() {
        System.out.println("改变一处页面");
    }

    @Override
    void plan() {
        System.out.println("给出页面变更计划");
    }
}
