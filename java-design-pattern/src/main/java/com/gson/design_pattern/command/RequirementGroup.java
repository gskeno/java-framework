package com.gson.design_pattern.command;

/**
 * Created by gaosong on 2018-01-16
 */
public class RequirementGroup extends Group {
    @Override
    void find() {
        System.out.println("找到需求组");
    }

    @Override
    void add() {
        System.out.println("增加一项需求");
    }

    @Override
    void delete() {
        System.out.println("删除一项需求");
    }

    @Override
    void change() {
        System.out.println("改变一项需求");
    }

    @Override
    void plan() {
        System.out.println("给出需求变更计划");
    }
}
