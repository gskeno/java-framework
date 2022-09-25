package com.gson.design_pattern.command;

/**
 * 抽象执行者(接收者)
 * Created by gaosong on 2018-01-16
 */
public abstract class Group {
    abstract void find();

    abstract void add();

    abstract void delete();

    abstract void change();

    abstract void plan();
}
