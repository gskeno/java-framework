package com.gsonkeno.pattern.command;

/**
 * Created by gaosong on 2018-01-16
 */
public class CodeGroup  extends Group{
    @Override
    void find() {
        System.out.println("找到代码组");
    }

    @Override
    void add() {
        System.out.println("增加一项功能");
    }

    @Override
    void delete() {
        System.out.println("删除一项功能");
    }

    @Override
    void change() {
        System.out.println("改变一项功能");
    }

    @Override
    void plan() {
        System.out.println("给出功能变更计划");
    }
}
